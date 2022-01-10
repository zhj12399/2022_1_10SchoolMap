import numpy as np
from PIL import Image
import csv

roads = []


def txtin():
    with open("liangxiang.txt", "r") as f:
        for line in f.readlines():
            line = line.strip('\n')
            road = []
            for i in line:
                road.append(int(i) * 255)
            roads.append(road)


def csvout():
    with open("liangxiangcsv.csv", "w", newline='') as csvfile:
        writer = csv.writer(csvfile)
        for row in roads:
            writer.writerow(row)


txtin()
img = Image.fromarray(np.uint8(roads))
img.show()
i_range = len(roads)
j_range = len(roads[0])
open_list = []
close_list = []

start_pos = [53, 1]
end_pos = [8, 27]

open_list.append([start_pos[0], start_pos[1], 0])
R_list = [] #父子关系
num = 0
path = []
while True:
    # F=G+H
    F_list = []
    # 遍历open'list，查找F最小节点
    for point in open_list:
        H = abs(point[0] - end_pos[0]) + abs(point[1] - end_pos[1])
        F_list.append(H + point[2])
    # print("F_list:",F_list)
    Fmin_point_index = F_list.index(min(F_list))
    Fmin_point = open_list.pop(Fmin_point_index)
    print("Fmin_point",Fmin_point)
    close_list.append([Fmin_point[0], Fmin_point[1]])
    # R_list.append(Fmin_point)
    # print("close_list",close_list)
    for i in range(Fmin_point[0] - 1, Fmin_point[0] + 2):
        for j in range(Fmin_point[1] - 1, Fmin_point[1] + 2):
            if 0 <= i < i_range and 0 <= j < j_range:
                # 是路
                if roads[i][j] == 255 and [i, j] not in close_list:
                    tmp_point = []
                    if abs(i - Fmin_point[0]) + abs(j - Fmin_point[1]) == 2:
                        tmp_point = [i, j, Fmin_point[2] + 14]
                    elif abs(i - Fmin_point[0]) + abs(j - Fmin_point[1]) == 1:
                        tmp_point = [i, j, Fmin_point[2] + 10]

                    flag = True
                    for open_list_item in open_list:
                        if tmp_point[0] == open_list_item[0] and tmp_point[1] == open_list_item[1]:
                            if tmp_point[2] < open_list_item[2]:  # 新点距离小于老点
                                print("tmp:", tmp_point, "open_item", open_list_item)
                                open_list.pop(open_list.index(open_list_item))
                                open_list.append(tmp_point)
                                R_list.append([[Fmin_point[0], Fmin_point[1]], [i, j]])
                                flag = False
                            else:
                                flag = False

                    if flag:
                        open_list.append(tmp_point)
                        R_list.append([[Fmin_point[0], Fmin_point[1]], [i, j]])

    end_flag = False
    for open_list_item in open_list:
        if open_list_item[0] == end_pos[0] and open_list_item[1] == end_pos[1]:
            print(num)
            print(R_list)
            end_flag = True

    if end_flag:
        tmp_point = end_pos
        while True:
            for item in R_list:
                if item[1][0] == tmp_point[0] and item[1][1] == tmp_point[1]:
                    path.append(item[0])
                    tmp_point = item[0]
                    break
            if tmp_point[0] == start_pos[0] and tmp_point[1] == start_pos[1]:
                break
        print(path)
        print(num)
        break

    print(open_list)
    # print(R_list)
    num += 1


for item in path:
    roads[item[0]][item[1]] = 127
img = Image.fromarray(np.uint8(roads))
img.show()
