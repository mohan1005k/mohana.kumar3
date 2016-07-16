from django.shortcuts import render
from django.http import HttpResponse

import datetime
from . import models
from models import Product
from models import Orders
from models import OrderHasProduct

from . filters import *
from django.db.models import Q
from django.db.models import Count
from django.db.models import F
from django.db.models import Sum
import json
from django.core.serializers.json import DjangoJSONEncoder
from django.http import JsonResponse
from rest_framework import status

from rest_framework.views import APIView

import json
# Create your views here.

from rest_framework.response import  Response
class MiddleWare(object):
    def process_response(self, request, response):
        print ('in response')
        if(response is not None):
            if(response._container is not None):
                response._container = ['{"data":'+response._container[0]+'}']

        return response


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
        date=F('orderid__orderdate'),orders=Count('orderid__id') ,   qty =Sum('quantityordered') ,sale_price = Sum(F('productid__sellingprice')*F('quantityordered')) , buy_price=Sum(F('productid__price')*F('quantityordered')) ,
        profit=Sum(F('productid__sellingprice')*F('quantityordered'))-Sum(F('productid__price')*F('quantityordered'))).order_by('-orderid__orderdate')

    resultsList=list(resultsQuerySet)

    for ob in resultsList:
        del ob['orderid__orderdate']

    return JsonResponse({'data': resultsList})

def productsummary(request):
    requiredcode=request.GET.get('code')
    category_name=request.GET.get('category_name')
    group_by = request.GET.get('group_by')
    if(group_by in ['categoryid__description']):
        if((requiredcode is not None) and (category_name is not None)):
            resultsQuerySet=Product.objects.filter(availability=True).filter(code=requiredcode).filter(categoryid__description=category_name).values('categoryid__description').annotate( count=Count('id'),category_id=F('categoryid__id'))
        elif(requiredcode is not None):
            resultsQuerySet = Product.objects.filter(availability=True).filter(code=requiredcode).values('categoryid__description').annotate(count=Count('id'),category_id=F('categoryid__id'))
        elif(category_name is not None):
            resultsQuerySet = Product.objects.filter(availability=True).filter(categoryid__description=category_name).values('categoryid__description').annotate(count=Count('id'),category_id=F('categoryid__id'))
        else:
            resultsQuerySet = Product.objects.filter(availability=True).values('categoryid__description').annotate(count=Count('id'),category_id=F('categoryid__id'))
        resultsList = list(resultsQuerySet)
    else:
        if ((requiredcode is not None) and (category_name is not None)):
            resultsQuerySet = Product.objects.filter(availability=True).filter(code=requiredcode).filter(
                categoryid__description=category_name).aggregate(count=Count('id'))
        elif (requiredcode is not None):
            resultsQuerySet = Product.objects.filter(availability=True).filter(code=requiredcode).aggregate(
                count=Count('id'))
        elif (category_name is not None):
            resultsQuerySet = Product.objects.filter(availability=True).filter(categoryid__description=category_name).aggregate(count=Count('id'))
        else:
            print('here')
            resultsQuerySet = Product.objects.filter(availability=True).aggregate(count=Count('id'))

        resultsList = [resultsQuerySet]
    return JsonResponse(resultsList,safe=False)

def ordersummary(request):
    group_by = request.GET.get('group_by')
    if group_by in ['category']:
        group_by = 'productid__categoryid__id'
    elif group_by in ['product']:
        group_by = 'productid__id'
    productCode=request.GET.get('orderlineitemproductcode')
    categoryName=request.GET.get('orderlineitemproductcategory__name')
    if(group_by in ['productid__id']):
        if(productCode is not None):
            resultQuerySet=OrderHasProduct.objects.filter(orderid__availability=True).filter(productid__code=productCode).values(group_by).annotate(count=Count('id'),product_id=F(group_by))
        else:
            resultQuerySet=OrderHasProduct.objects.filter(orderid__availability=True).values(group_by).annotate(count=Count('id'),product_id=F(group_by))
        resultList = list(resultQuerySet)
    elif group_by in ['productid__categoryid__id']:
        if(categoryName is not None):
            resultQuerySet = OrderHasProduct.objects.filter(orderid__availability=True).filter(productid__categoryid__description=categoryName).values(group_by).annotate(count=Count('id'), category_id=F(group_by))
        else:
            resultQuerySet = OrderHasProduct.objects.filter(orderid__availability=True).values(group_by).annotate(count=Count('id'), product_id=F(group_by))
        resultList = list(resultQuerySet)
    else:
        resultQuerySet=OrderHasProduct.objects.filter(orderid__availability=True);
        if (categoryName is not None):
            resultQuerySet = resultQuerySet.filter(productid__categoryid__description=categoryName)
        if(productCode is not None):
            resultQuerySet=resultQuerySet.filter(productid__code=productCode)
        resultQuerySet = resultQuerySet.aggregate(count=Count('id'))
        resultList=[resultQuerySet]
    return JsonResponse(resultList,safe=False)





