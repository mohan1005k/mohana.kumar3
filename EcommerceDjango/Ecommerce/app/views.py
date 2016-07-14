from django.shortcuts import render
from django.http import HttpResponse

import datetime
from . import models
from models import Product
from models import Orders
from models import OrderHasProduct

from django.db.models import Q
from django.db.models import Count
from django.db.models import F
from django.db.models import Sum
import json
from django.core.serializers.json import DjangoJSONEncoder
from django.http import JsonResponse
# Create your views here.

def reports(request):

    startDate=request.GET.get('startDate')
    endDate=request.GET.get('endDate')

    if(startDate is None):
        startDate="01/01/1900"
    if(endDate is None):
        endDate="01/01/3000"

    startDate=datetime.datetime.strptime(startDate,"%m/%d/%Y").strftime("%Y-%m-%d")
    endDate = datetime.datetime.strptime(endDate, "%m/%d/%Y").strftime("%Y-%m-%d")
    print (startDate)
    print (endDate)
    resultsQuerySet=OrderHasProduct.objects.filter(Q(orderid__orderdate__lte=endDate) &
        Q(orderid__orderdate__gte=startDate) ).values('orderid__orderdate').annotate(
        date=F('orderid__orderdate'),orders=Count('orderid__orderid') ,   qty =Sum('quantityordered') ,sale_price = Sum(F('productid__sellingprice')*F('quantityordered')) , buy_price=Sum(F('productid__cost')*F('quantityordered')) ,
        profit=Sum(F('productid__sellingprice')*F('quantityordered'))-Sum(F('productid__cost')*F('quantityordered'))).order_by('-orderid__orderdate')

    resultsList=list(resultsQuerySet)

    for ob in resultsList:
        del ob['orderid__orderdate']

    return JsonResponse({'data': resultsList})
