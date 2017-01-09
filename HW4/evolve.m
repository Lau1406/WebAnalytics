function G_evo = evolve(G_org, num, intensity, evolve_type)
    if evolve_type == 0
        G_evo = uniform_remove_edges(G_org, num, intensity);
    end
end

function G_evo = uniform_remove_edges(G_org, num, num_edges)
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