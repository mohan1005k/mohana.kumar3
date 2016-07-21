from rest_framework import viewsets,mixins
from . models import Product,Orders,Categories,OrderHasProduct,Feedback

from . serializers import ProductSerializer,OrdersSerializer,UserSerializer,CategoriesSerializer,OrderHasProductSerializer,FeedbackSerializer
from django.http import JsonResponse,HttpResponse
from rest_framework.response import  Response
from rest_framework import status
from . filters import *
from rest_framework.renderers import JSONRenderer

class HealthViewSet(viewsets.ModelViewSet):
    serializer_class = UserSerializer

    def get_queryset(self):
        return HttpResponse(status=status.HTTP_200_OK)

    def create(self, request, *args, **kwargs):



        return HttpResponse(status=status.HTTP_200_OK)



class OrderHasProductViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    filter_class=OrderHasProductFilter
    serializer_class = OrderHasProductSerializer
    queryset=OrderHasProduct.objects.all()
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
        #order_has_product_instance=OrderHasProduct.objects.get_or_create(orderid=order_instance,productid=product_instance,price=request.data['price'],quantityordered=request.data['quantityordered'])[0]
        order_has_product_instance = OrderHasProduct.objects.get_or_create(orderid=order_instance, productid=product_instance)[0]
        order_has_product_instance.price = request.data['price']
        old_quantity=0
        if(order_has_product_instance.quantityordered is not None):
            old_quantity=order_has_product_instance.quantityordered
        order_has_product_instance.quantityordered = old_quantity+ request.data['quantityordered']
        order_has_product_instance.save();
        serializer = self.get_serializer(order_has_product_instance)
        #content={'data':serializer.data}
        return Response(serializer.data)


class CategoriesViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = CategoriesSerializer
    queryset = Categories.objects.all()

    def list(self, request, *args, **kwargs):
        resultQuerySet = Categories.objects.all()
        serializer = self.get_serializer(resultQuerySet, many=True)
        # content = {'data': serializer.data}
        return Response(serializer.data)


class ProductViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = ProductSerializer
    queryset = Product.objects.all()

    def list(self, request, *args, **kwargs):

        resultQuerySet = Product.objects.filter(availability=True)
        serializer = self.get_serializer(resultQuerySet, many=True)
        # content = {'data': serializer.data}
        return Response(serializer.data)


    def destroy(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            pk=value
        Product.objects.filter(id=pk).update(availability=False)
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)



class UserViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = UserSerializer

    def get_queryset(self):
        return User.objects.all()

    def create(self, request, *args, **kwargs):


        user_instance = User.objects.get_or_create(username=request.data['username'], address=request.data['address'])[0]

        serializer = self.get_serializer(user_instance)
        # content={'data':serializer.data}
        return Response(serializer.data)

class OrdersViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = OrdersSerializer

    def get_queryset(self):
        return Orders.objects.filter(availability=True).all()

    def destroy(self, request, *args, **kwargs):
        for key, value in kwargs.iteritems():
            pk = value
        Orders.objects.filter(id=pk).update(availability=False)
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)

class FeedbackViewSet(mixins.CreateModelMixin,mixins.UpdateModelMixin, mixins.ListModelMixin,mixins.DestroyModelMixin,mixins.RetrieveModelMixin, viewsets.GenericViewSet):
    serializer_class = FeedbackSerializer
    queryset = Feedback.objects.all()

    def create(self, request, *args, **kwargs):


        user_instance = User.objects.get_or_create(username=request.data['username'])[0]
        feedback_instance=Feedback.objects.get_or_create(description=request.data['description'],userid=user_instance)[0]
        serializer = self.get_serializer(feedback_instance)
        # content={'data':serializer.data}
        return Response(serializer.data)