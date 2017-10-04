def boarders(s):
    b = [0]*len(s)
    for i in range(1, len(s)):
        k = b[i-1]
        while k>0 and s[k] != s[i]:
            k = b[k-1]
        if s[k] == s[i]:
            b[i] = k+1
    return b
def main():
    s = raw_input()
    t = raw_input()
    if len(s) != len(t):
        print('No')
        return
    rt = t[::-1]
    rev = s[::-1] + '#' + rt
    b = boarders(rev)[-1]
    for i in range(len(s)-b):
        if s[i] != rt[i]:
            print('No')
            return
    print('Yes')
    print(len(s)-b)


if __name__ == '__main__':
    main()
