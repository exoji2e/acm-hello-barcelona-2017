import math, sys
print('\n'.join(map(lambda x: str(0 if x == 0 else x - (1<<int(math.log(x+1,2))) + 1), [int(x.split()[1]) for x in sys.stdin.readlines()])))
