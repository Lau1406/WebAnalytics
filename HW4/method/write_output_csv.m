%Write the results to a csv file.
function write_output_csv(output, data_rank, header)
    output_id = fopen(output, 'w');
    fprintf(output_id, header);
    fclose(output_id);

    dlmwrite(output, data_rank, '-append', 'delimiter', ';', 'precision', 10, 'roffset', 1);
end
