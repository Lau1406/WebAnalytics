G = load_data(0);
size = get_data_size(0);
x = pagerank(G, size, 0);
plot(x);

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
    A = G*D;

    x = ones(num,1)/num;
    oldx = zeros(num,1);
    while norm(x - oldx) > .01
        oldx = x;
        x = A*x;
    end

    pagevector = x/sum(x);
end

function cmp = cmp_page_rank(baselineranking, ranking, cmp_algorithm_nr)
    cmp = 1;
end