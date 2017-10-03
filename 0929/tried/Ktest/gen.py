import sys
for l in range(1, 10):
    for n in range(1, 53):
        f = open(str(l) + '-' + str(n) +'.in', 'w')
        f.write(str(l) + ' ' + str(n) + '\n')
