import sys
def h2d(hexx):
    if '0' <= hexx <= '9':
        return ord(hexx) - ord('0')
    else:
        return ord(hexx) - ord('A') + 10

def mkhex(hexx):
    if 0 <= hexx <= 9:
        return str(hexx)
    else:
        return chr(hexx - 10 + ord('A'))


def produce(vals):
    out = ''
    for x in vals:
        if x == 0:
            s = ' 0'
        else:
            s = ' '
            while x > 0:
                s += mkhex(x%16)
                x = x//16
        out = out + s[::-1]
    return out


lines = sys.stdin.readlines()
bl = [x for i in range(len(lines)) for x in lines[i].split()]
out = []
vals = []
starts = {'0': 1, '1':1, '2':1, '3':1, '4':1, '5':1, '6':1, '7':1, 'C':2, 'D':2, 'E':3, 'F':-1}
currHex = 0
left = 0
sub = {1: 0, 2: 192, 3: 224, 4: 240, 5: 248, 6:252}
for i, b in enumerate(bl):
    if b[0] in starts:
        if left != 0 or b == 'FF' or b == 'FE':
            if len(vals) >= 3: out.append(produce(vals))
            vals = []
            if b == 'FF' or b == 'FE':
                left = 0
                continue
        left = starts[b[0]]
        if left == -1:
            if h2d(b[1]) < 8:
                left = 4
            elif h2d(b[1]) < 12:
                left = 5
            else:
                left = 6
        nbr = 16*h2d(b[0]) + h2d(b[1]) - sub[left]
        currHex = nbr
        left-=1
    else:
        nbr = 16*h2d(b[0]) + h2d(b[1]) - 128
        currHex = currHex*64 + nbr
        left -= 1

    if left == 0:
        vals.append(currHex)
        currHex = 0


if len(vals) >= 3: out.append(produce(vals))

for o in out:
    print(o)


