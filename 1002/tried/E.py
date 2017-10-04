import math
out = []
for _ in range(int(input())):
    out.append('')
    x1, y1, r1 = map(int, input().split())
    x2, y2, r2 = map(int, input().split())
    if x1 == x2 and y1 == y2 and r1 == r2:
        out.append('I can\'t count them - too many points :(')
        continue
    if r1 > r2:
        x1, y1, r1, x2, y2, r2 = (x2, y2, r2, x1, y1, r1)
    dist2 = (x1 - x2)*(x1-x2) + (y1 - y2)*(y1 - y2)
    rsq = (r1 + r2)*(r1 + r2)
    if dist2 > rsq or dist2 < (r1-r2)*(r1-r2):
        out.append('There are no points!!!')
        continue
    elif dist2 == rsq:
        out.append('There are only 1 of them....')
        cx = x1 + (x2-x1)*r1/(r1+r2)
        cy = y1 + (y2-y1)*r1/(r1+r2)
        out.append(str(cx) + ' ' + str(cy))
        continue
    elif dist2 == (r1-r2)*(r1-r2):
        out.append('There are only 1 of them....')
        cx = x1 - (x2-x1)*r1/(r2-r1)
        cy = y1 - (y2-y1)*r1/(r2-r1)
        out.append(str(cx) + ' ' + str(cy))
        continue

    out.append('There are only 2 of them....')
    d = math.sqrt(dist2)
    f = (r1*r1 - r2*r2 + dist2)/(2*dist2)
    xf = x1 + f*(x2-x1)
    yf = y1 + f*(y2-y1)
    # Comment about midpoint inside
    dx = xf-x1
    dy = yf-y1
    h = math.sqrt(r1*r1 - dx*dx - dy*dy)
    norm = abs(math.hypot(dx, dy))
    p1 = (xf + h*(-dy)/norm, yf + h*(dx)/norm)
    p2 = (xf + h*(dy)/norm, yf + h*(-dx)/norm)
    pts = sorted([p1, p2])
    out.append(str(pts[0][0]) + ' ' + str(pts[0][1]))
    out.append(str(pts[1][0]) + ' ' + str(pts[1][1]))

out = out[1:]
print('\n'.join(out))
    




