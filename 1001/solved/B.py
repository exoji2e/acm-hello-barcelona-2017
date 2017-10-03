T = int(raw_input())
for _ in range(T):
    n = int(raw_input())
    strs = [raw_input() for _ in range(n)]
    s = max(strs, key=lambda x: len(x))
    fail = False
    for ss in strs:
        for i in range(len(ss)):
            if ss[i] != s[i]:
                fail = True
                break
        if fail:
            break
    print('Impossible' if fail else len(s))
