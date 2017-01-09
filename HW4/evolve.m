function G_evo = evolve(G_org, intensity, evolve_type)
    if evolve_type == 0
        G_evo = uniform_remove_edges(G_org, intensity);
    elseif evolve_type == 1
        G_evo = uniform_remove_nodes(G_org, intensity);
    elseif evolve_type == 3
        G_evo = weighted_remove_edge(G_org, intensity, 'edge_degree');
    end
end

function G_evo = uniform_remove_edges(G_org, num_edges)
    num = size(G_org,1);
    G_evo = G_org;
    nof_edges = sum(sum(G_evo));
    edges_c = sum(G_evo);
    for i = num_edges:-1:1
        r = floor(nof_edges * rand) + 1;
        j = 1;
        while r > 0
            r = r - edges_c(j);
            j = j + 1;
        end
        j = j -1;
        ii = num;
        while r <= 0
            if G_evo(ii, j) == 1
                r = r + 1;
            end
            ii = ii - 1;
        end
        ii = ii + 1;
        G_evo(ii,j) = 0;
        edges_c(j) = edges_c(j) - 1;
        nof_edges = nof_edges - 1;
    end
end

function G_evo = uniform_remove_nodes(G_org, num_nodes)
    num = size(G_org,1);
    G_evo = G_org;
    for i = 1:num_nodes
        r = floor(num * rand) + 1;
        G_evo(r,:) = [];
        G_evo(:,r) = [];
        num = num - 1;
    end
end

function G_evo = weighted_remove_edge(G_org, num_nodes, weigher)
    G_evo = G_org;
    num = size(G_evo,1);
    for n = num:-1:(num + 1 - num_nodes)
        nodes = 1:n;
        % add realmin to prevent all 0 items
        weights = arrayfun(@(x) feval(weigher, G_evo, x) + realmin, nodes);
        node = randsample(nodes, 1, true, weights);
        disp(node);
        G_evo(node, :) = [];
        G_evo(:, node) = [];
    end
end

function weight = incoming_edge_degree(G, i)
    weight = full(sum(G(:,i)));
end
function weight = outgoing_edge_degree(G, i)
    weight = full(sum(G(i,:)));
end
function weight = edge_degree(G, i)
    weight = incoming_edge_degree(G, i) + outgoing_edge_degree(G, i);
end

function data = stub_data(dataset_nr)
    data = sparse([0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0]);
    
    data(3,7) = 1;
    data(7,2) = 1;
    data(1,2) = 1;
    data(2,2) = 1;
    data(3,2) = 1;
    data(5,2) = 1;
    data(4,2) = 1;
    data(3,2) = 1;
    data(7,3) = 1;
    data(2,3) = 1;
    data(4,3) = 1;
end