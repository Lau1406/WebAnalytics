%the file that should be loaded
transition_file = 'transition.txt';

%Load the original matrix, of which the values can be found within the corresponding files.
A = load(transition_file, '-ascii');
num = length(1:max(A(:)));

all_pageranks = {};
all_ranks = {};

%Calculate the pageranks and the rank they got
single_pagerank = feigensolver_with_teleport(A, num);
all_pageranks = [all_pageranks single_pagerank];
single_rank = rank_pagerank(single_pagerank);
all_ranks = [all_ranks single_rank];

single_pagerank = feigensolver_without_teleport(A, num);
all_pageranks = [all_pageranks single_pagerank];
single_rank = rank_pagerank(single_pagerank);
all_ranks = [all_ranks single_rank];

single_pagerank = fpower_with_teleport(A, num);
all_pageranks = [all_pageranks single_pagerank];
single_rank = rank_pagerank(single_pagerank);
all_ranks = [all_ranks single_rank];

single_pagerank = fpower_without_teleport(A, num);
all_pageranks = [all_pageranks single_pagerank];
single_rank = rank_pagerank(single_pagerank);
all_ranks = [all_ranks single_rank];

single_pagerank = fpower_with_teleport_and_sparse(A, num);
all_pageranks = [all_pageranks single_pagerank];
single_rank = rank_pagerank(single_pagerank);
all_ranks = [all_ranks single_rank];

%print the results in a csv
output = 'pagerank.csv';
header = ['pagerank eigensolver with teleport;' 'pagerank eigensolver without teleport;' 'pagerank power with teleport;' 'pagerank power without teleport pagerank;' 'pagerank power with teleport and sparse;' 'rank eigensolver with teleport;' 'rank eigensolver without teleport;' 'rank power with teleport;' 'rank power without teleport pagerank;' 'rank power with teleport and sparse;'];

%Write the output to a csv file.
write_output_csv(output, [all_pageranks all_ranks], header);

%boxplot where we can see all the pageranks from the different methods
boxplot(cell2mat(all_pageranks), {'eigensolver with teleport;' 'eigensolver without teleport;' 'power with teleport;' 'power without teleport pagerank;' 'power with teleport and sparse;'});
title('PageRank');

