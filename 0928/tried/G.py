import sys
from collections import Counter
def main():
    lines = sys.stdin.readlines()
    l2id = {}
    labels = [False]*len(lines)
    e3id = [0]*len(lines)
    for i, line in enumerate(lines):
        if ':' in line:
            labels[i] = True
            l2id[line.split()[0][:-1] + ';'] = i
            
        if 'expr' in line and (not line.split()[-1] in l2id):
            e3id[i] = l2id[line.split()[-1]]
            print('bad')
            return

    e2id = [0]*len(lines)
    for i in range(len(lines)):
        if labels[i]:
            e2id[i] = i
        else:
            e2id[i] = e2id[i-1]

    for i in range(len(lines)):
        if 'expr' in lines[i]:
            if e3id[i] != e2id[i]:
                print('bad')
                return

    print('good')


if __name__ == '__main__':
    main()
