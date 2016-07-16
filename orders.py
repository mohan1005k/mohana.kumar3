import csv;
import re
import time
import datetime
import random
from random import randrange
from datetime import timedelta
import radar



user_list=[]
status_list=[]
date_list=[]
status_list=[]
status_list.append("CREATED")
status_list.append("PROGRESS")
status_list.append("SHIPPED")
status_list.append("COMPLETED")

#Generating random dates

for iter in range(1,20):
	startDate='2016-07-01'
	endDate='2016-07-15'
	date_list.append(radar.random_datetime(start=startDate, stop=endDate))

#Fetching UserIds from existing Users table-Required for foreign key relation in Orders table

with open('User_view.csv', 'rb') as csvfile:
	spamreader = csv.reader(csvfile, delimiter=',')
	index=0;
	for row in spamreader:
		index=index+1
		if(index is 1):
			continue;
		user_list.append(row[0]);

#Populating Orders table with random values

with open('order_populate.csv', 'wb') as csvfile:
	spamwriter = csv.writer(csvfile, delimiter=',')
	availableorderid=300
	spamwriter.writerow(["OrderId","Status","OrderDate","Availability","FkUserId"])
	for iter in range(1  ,1000000):
		orderid=availableorderid+iter
		orderstatus=random.choice(status_list)
		orderdate=random.choice(date_list)
		availability=1
		FkUserId=random.choice(user_list)
		spamwriter.writerow([orderid,orderstatus,orderdate,availability,FkUserId])


	


