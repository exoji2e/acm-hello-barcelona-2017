def main():
    a = input()
    b = input()
    a, b = (a, b) if len(a) <= len(b) else (b, a)

    sa = list(filter(lambda x: x[1] == '*', enumerate(a)))
    sb = list(filter(lambda x: x[1] == '*', enumerate(b)))
    
    if not sa and not sb:
        slen = len(b) - len(a)

        if slen == 0:
            tot = 0
            x = []
            for i in range(len(a)):
                if a[i] != b[i]:
                    x.append('?')
                else:
                    x.append(a[i])
            print(''.join(x))
            return
            
        cnt = 10000
        best = ""
        for st in range(len(a)+1):
            x = []
            for i in range(st):
                if a[i] != b[i]:
                    x.append('?')
                else:
                    x.append(a[i])
            x.append('*')
            for i in range(len(a) - st):
                if a[i] != b[i + slen]:
                    x.append('?')
                else:
                    x.append(a[i])
            c = x.count('?')
            if c < cnt:
                cnt = c
                best = ''.join(x)

        print(''.join(best))
    else:
        l = len(b)
        if sa:
            l = sa[0][0]
            a = a[0:sa[0][0] + 1] + a[sa[-1][0] + 1:]
        if sb:
            b = b[0:sb[0][0] + 1] + b[sb[-1][0] + 1:]
            l = min(l, sb[0][0])
        if sa != [] and sb != []:
            x = []
            for i in range(l):
                if a[i] != b[i]:
                    x.append('?')
                else:
                    x.append(a[i])
            y = []
            for i in range(1, min(len(a), len(b)) - l):
                if a[-i] != b[-i]:
                    x.append('?')
                else:
                    x.append(a[-i])
            print(''.join(x + ['*'] + y))
            return
        elif sa:
            a, b = (b, a)

        if len(a) < len(b) -1:
            for i in range()



            

                
                



        l = len(b)
        r = len(b)
        if sb:
            l = sb[0][0]
            r = len(b) - sb[0][0] - 1
        if sa:
            l = min(l, sa[0][0])
            r = min(l, len(a) - sa[0][0] - 1)
        
         



if __name__ == '__main__':
    main()

