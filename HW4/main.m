
test_dataset(2);


function test_dataset(datanr)
    G = load_data(datanr);
    baseline = pagerank(G, 0);
    nof_nodes = size(G,1);
    nof_edges = sum(G);
    scores = test_evolve(G, baseline, floor(nof_edges/10),0);
    dlmwrite(strcat('10procent_of_edges_uniformly_dataset_', num2str(datanr)), scores);
end

function scores = test_evolve(G, baseline, intensity, algorithm_nr)
    experiment_iterations = 1;
    scores = zeros(1,experiment_iterations);
    for i = 1:experiment_iterations
        [G_evo, baseline_evo] = evolve(G, intensity, baseline, algorithm_nr);
        rank = pagerank(G_evo, 0);
        %todo: use previously computed pagerank
        scores(i) = cmp_page_rank(baseline_evo, rank, 1);
    end
end 

function data = load_data(dataset_nr)
    if dataset_nr == 0
        A = load('transition.txt', '-ascii');
    elseif dataset_nr == 1
        A = load('soc-hamsterster.edges', '-ascii');
    else
        A = load('ego-facebook.edges', '-ascii');
    end
    i = A(:,1);
    j = A(:,2);
    num = max(max(i),max(j));

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