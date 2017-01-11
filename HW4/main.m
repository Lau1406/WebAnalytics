cmp_evolution(0, 0, 0, 0, 100);

function cmp_score = cmp_evolution(dataset_nr, pagerank_algorithm_nr, cmp_algorithm_nr, evolve_type, evolve_strength)
    G = load_data(dataset_nr);
    G_evo = evolve(G, evolve_strength, evolve_type);
    baseline = pagerank(G, pagerank_algorithm_nr);
    %todo: use previously computed pagerank
    ranking = pagerank(G_evo, pagerank_algorithm_nr);
    cmp_score = cmp_page_rank(baseline, ranking, cmp_algorithm_nr);
end

function data = load_data(dataset_nr)
    A = load('transition.txt', '-ascii');
    i = A(:,1);
    j = A(:,2);
    num = 1490;

    data = sparse(i,j,1,num,num);
end

function pagevector = pagerank(G, pagerank_algorithm_nr)
    num = size(G,1);
    c = full(sum(G));
    k = find(c~=0);
    D = sparse(k,k,1./c(k),num,num);
    e = ones(num,1);

    % adding the teleport
    p = 0.85;
    z = ((1-p)*(c~=0)+(c==0))/num;
    A = p*G*D+e*z;

    x = e/num;
    oldx = zeros(num,1);
    while norm(x - oldx) > .00001
        oldx = x;
        x = A*x;
    end

    pagevector = x/sum(x);
end

function cmp = cmp_page_rank(pageranks_base, pageranks, cmp_algorithm_nr)
    if cmp_algorithm_nr == 0
        error_rank = 0;
        
        % we need to sort the pagerank lists and create ranking lists
        sort(pageranks);
        sort(pageranks_base);
        
        [~,~,ranking] = unique(pageranks * -1); % -1 flips the ranking order, 1 being the highest
        [~,~,ranking_base] = unique(pageranks_base * -1);
        
        disp(ranking);
        
        for i=1:length(pageranks)
            rank = ranking(i);
            rank_base = ranking_base(i);
            
            error_rank = error_rank + ( abs(rank - rank_base) / rank_base );
        end
        cmp = error_rank;
    elseif cmp_algorithm_nr == 1
        error_value = 0;
        
        for i=1:length(pageranks)
            pgrank = pageranks(i);
            pgrank_base = pageranks_base(i);
            
            error_value = error_value + ( abs(pgrank - pgrank_base) / pgrank_base );
        end
        cmp = error_value;
    end
end