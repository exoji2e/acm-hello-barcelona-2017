T = int(input())
for _ in range(T):
    n = input()
    out = []
    m4 = len(n)%4
    n = ['0']*((4 - m4)%4) + list(n)
    for i in range(len(n)//4):
        s = n[i*4:i*4 + 4]
        x = 8*(1 if s[0] == '1' else 0)
        x += 4*(1 if s[1] == '1' else 0)
        x += 2*(1 if s[2] == '1' else 0)
        x += (1 if s[3] == '1' else 0)
        if x<10:
            out.append(str(x))
        else:
            out.append(chr(x-10 + ord('A')))
    print(''.join(out))
