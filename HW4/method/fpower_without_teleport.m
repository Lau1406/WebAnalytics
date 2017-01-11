function x = fpower_without_teleport(A, num)
    i = A(:,1);
    j = A(:,2);

    G = sparse(i,j,1,num,num);
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

    x = x/sum(x);
end
