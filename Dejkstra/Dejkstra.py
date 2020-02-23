import math


def show_graph(matrix):
    edge_list = []
    for vertex in matrix:
        for sub_vertex in vertex:
            if not(sub_vertex == 0) and not(sub_vertex == math.inf):
                edge_list.add()

def init_graph():
    adjacency_matrix = [[math.inf]]
    return adjacency_matrix


def add_vertex(adjacency_matrix):
    for item in adjacency_matrix:
        item.append(math.inf)
    new_line = [math.inf for i in range(len(adjacency_matrix[0]) - 1)] + [math.inf]
    adjacency_matrix.append(new_line)
    return adjacency_matrix


#Считаем, что граф !НАПРАВЛЕННЫЙ#
def add_edge(vertex1, vertex2, weight, matrix):
    matrix[vertex1][vertex2] = matrix[vertex2][vertex1] = weight



def fill_matrix(inp, size):
    
    matrix = init_graph()
    for i in range(size - 1):
        add_vertex(matrix)
        
    adges = inp.split(', ')
    for i in adges:
        num = ''
        j = len(i) - 1
        while j > 0:
            if i[j].isnumeric():
                num = i[j] + num
            else:
                break
            j -= 1
        #print('added adge ({}; {}), weight = {}'.format(int(i[3]) - 1, int(i[6]) - 1, int(num) ))
        matrix[int(i[3]) - 1][int(i[6]) -1] = matrix[int(i[6]) -1][int(i[3]) - 1] = int(num)
        
        
    return matrix
            

def getShortestPath(u, v, next_v, d):
    u -= 1
    v -= 1
    print('len = ', d[u][v])
    if d[u][v] == math.inf:
        print( "No path found")                 
    c = u
   
    while c != v:
        print (c, end = ' -> ')
        c = next_v[c][v]
    print (v)
    
def Dijkstra(N, S, matrix):
	valid = [True]*N        
	weight = [1000000]*N
	weight[S] = 0
	for i in range(N):
		min_weight = 1000001
		ID_min_weight = -1
		for i in range(len(weight)):
			if valid[i] and weight[i] < min_weight:
				min_weight = weight[i]
				ID_min_weight = i
		for i in range(N):
			if weight[ID_min_weight] + matrix[ID_min_weight][i] < weight[i]:
				weight[i] = weight[ID_min_weight] + matrix[ID_min_weight][i]
		valid[ID_min_weight] = False
	return weight


def inp_func():
	print('Количество ребер :')
	n = int(input())

	print('Количество вершин :')
	v = int(input())


	print('ребра в формате:   w(v1,v5) = 12 - ребро из 1 вершины в 5 длинной 12 ')
	inp = ''
	for i in range(n):
		s = input()
		inp += s + ', '
		
		num = ''
		j = len(s) - 1
		while j > 0:
			if s[j].isnumeric():
				num = s[j] + num
			else:
				break
			j -= 1

		print('added adge ({}; {}), weight = {}'.format(int(s[3]) , int(s[6]) , int(num) ))


	print('Введите вершину, до которой хотите узнать расстояние')
	x = int(input())
	
	return v, inp[:-2], x



n, inp, x = inp_func()

matrix = fill_matrix(inp, n)

x = Dijkstra(n, x, matrix)
print('номер вершины: ', end = ' ')
for i in range(n):
	print(i , end = ' ')
print()
print('расстояние:     ', end = ' ')
for i in x:
	print(i, end = ' ')
print()
