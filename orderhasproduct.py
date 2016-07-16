import csv;
import re
import time
import datetime
import random
from random import randint
productid_list=[]
orderid_list=[]

with open('Product_view.csv', 'rb') as csvfile:
	spamreader = csv.reader(csvfile, delimiter=',')
	index=0;
	for row in spamreader:
		index=index+1
		if(index is 1):
			continue;
		productid_list.append(row[0]);

with open('order_populate.csv', 'rb') as csvfile:
	spamreader = csv.reader(csvfile, delimiter=',')
	i=0;
	for row in spamreader:
		index=index+1
		if(index is 1):
			continue;
		orderid_list.append(row[0]);


with open('order_has_product_populate.csv', 'wb') as csvfile:
	spamwriter = csv.writer(csvfile, delimiter=',')
	availableorderhasproductid=10
	maxprice=10000
	maxorderquantity=100
	spamwriter.writerow(["id","QuantityOrdered","Price","OrderId","ProductId"])
	for iter in range(1  ,1000000):
		id=availableorderhasproductid+iter
		price=randint(1,maxprice)
		quantityordered=randint(1,maxorderquantity)
		orderid=random.choice(orderid_list)
		productid=random.choice(productid_list)
		spamwriter.writerow([id,quantityordered,price,orderid,productid])


	


