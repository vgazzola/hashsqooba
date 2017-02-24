
with open("/Users/apagano/Desktop/Sqooba/GoogleHashCode/hashsqooba/src/main/resources/problems/work/me_at_the_zoo.in", 'rb') as f:
    first_line = f.next().split()
    num_endpoints = int(first_line[1])
    sizes = f.next().split()
    sizes=[int(x) for x in sizes]
    videos_id = {key: 0 for key in xrange(0, len(sizes))}
    endpoints = {key: [] for key in xrange(0, num_endpoints-1)}
    videos_requests = dict(videos_id)
    for line in f:
        line_split = line.split()

        if len(line_split)==2:
            if int(line_split[0]) > num_endpoints:
                endpoint = int(line_split[1])
            if int(line_split[0])<num_endpoints:
                endpoints[endpoint].append((int(line_split[0]), int(line_split[1])))
        if len(line_split)==3:
            videos_requests[int(line_split[0])]+=int(line_split[2])


    metric=[]
    for i, id in enumerate(videos_id):
        metric.append(videos_requests[id]/sizes[i])

    id_by_metric = [x for (y, x) in sorted(zip(metric, videos_id))]

