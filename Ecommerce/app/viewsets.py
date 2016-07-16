from rest_framework import viewsets,mixins
from . models import Product,Orders,Categories,OrderHasProduct

from . serializers import ProductSerializer,OrdersSerializer,UserSerializer,CategoriesSerializer,OrderHasProductSerializer
from django.http import JsonResponse,HttpResponse
from rest_framework.response import  Response
from rest_framework import status
from . filters import *
from rest_framework.renderers import JSONRenderer


class OrderHasProductViewSet(mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.CreateModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    filter_class=OrderHasProductFilter
    serializer_class = OrderHasProductSerializer
    queryset=OrderHasProduct.objects.all()
    #renderer_classes = (JSONRenderer,)
    def list(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            if(key=="orderid"):
                orderid_pk=value
        resultQuerySet=OrderHasProduct.objects.filter(orderid__id=orderid_pk)
        serializer= self.get_serializer(resultQuerySet,many=True)
        #content = {'data': serializer.data}
        return Response(serializer.data)

    def create(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            if (key == "orderid"):
                orderid_pk = value
        order_instance=(Orders.objects.get_or_create(id=orderid_pk))[0]
        product_instance=(Product.objects.get_or_create(id=request.data['product_id']))[0]
        order_has_product_instance=OrderHasProduct.objects.get_or_create(orderid=order_instance,productid=product_instance,price=request.data['price'])[0]

        serializer = self.get_serializer(order_has_product_instance)
        #content={'data':serializer.data}
        return Response(serializer.data)


class CategoriesViewSet( viewsets.ModelViewSet):
    serializer_class = CategoriesSerializer
    def get_queryset(self):
        return Categories.objects.all()

class ProductViewSet(mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = ProductSerializer

    def get_queryset(self):
        print('here in get product')
        return Product.objects.filter(availability=True).all()

    def destroy(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            pk=value
        Product.objects.filter(id=pk).update(availability=False)
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


class UserViewSet(viewsets.ModelViewSet):
    serializer_class = UserSerializer

    def get_queryset(self):
        return Product.objects.all()

class OrdersViewSet(viewsets.ModelViewSet):
    serializer_class = OrdersSerializer

    def get_queryset(self):
        return Orders.objects.filter(availability=True).all()

    def destroy(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            pk = value
        Orders.objects.filter(id=pk).update(availability=False)
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)

