function rank = rank_pagerank(pagerank)
    pagerank_sorted = sort(pagerank, 'descend');
    [~, rank] = ismember(pagerank, pagerank_sorted);
end