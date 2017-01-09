G = load_data(0);
num = get_data_size(0);
x = pagerank(G, num, 0);
plot(x);
%disp(cmp_evolution(0,0,0,0,1));

function cmp_score = cmp_evolution(dataset_nr, pagerank_algorithm_nr, cmp_algorithm_nr, evolve_type, evolve_strength)
    G = load_data(dataset_nr);
    num = get_data_size(dataset_nr);
    G_evo = evolve(G, num, evolve_strength, evolve_type);
    baseline = pagerank(G, num, pagerank_algorithm_nr);
    ranking = pagerank(G_evo, num, pagerank_algorithm_nr);
    cmp_score = cmp_page_rank(baseline, ranking, cmp_algorithm_nr);
end

function data = load_data(dataset_nr)
    A = load('transition.txt', '-ascii');
    i = A(:,1);
    j = A(:,2);
    num = get_data_size(dataset_nr);

    data = sparse(i,j,1,num,num);
end
function size = get_data_size(dataset_nr)
    size = 1490;
end

function pagevector = pagerank(G, num, pagerank_algorithm_nr)
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