G = load_data(0);
x = pagerank(G, 0);
plot(x);

function cmp_score = cmp_evolution(dataset_nr, pagerank_algorithm_nr, cmp_algorithm_nr, evolve_type, evolve_strength)
    G = load_data(dataset_nr);
    G_evo = evolve(G, evolve_strength, evolve_type);
    baseline = pagerank(G, pagerank_algorithm_nr);
    %todo: use previously computed pagerank
    ranking = pagerank(G_evo, pagerank_algorithm_nr);
    cmp_score = cmp_page_rank(baseline, ranking, cmp_algorithm_nr);
end

function data = stub_data(dataset_nr)
    data = sparse([0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0]);
    
    data(3,7) = 1;
    data(7,2) = 1;
    data(3,2) = 1;
    data(7,3) = 1;
    data(2,3) = 1;
    data(4,3) = 1;
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

function cmp = cmp_page_rank(baselineranking, ranking, cmp_algorithm_nr)
    cmp = 1;
end