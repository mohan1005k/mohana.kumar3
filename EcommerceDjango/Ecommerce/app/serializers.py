from rest_framework import serializers
from . models import Product
from . models import Orders
from . models import Categories
from . models import User
from . models import OrderHasProduct,Feedback

class FeedbackSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source='userid.username',required=False)


    class Meta:
        model = Feedback
        field = ('description','username')

    def create(self, validated_data):
        return Feedback.objects.create(self.validated_data)


#Serializer for User
class UserSerializer(serializers.ModelSerializer):

    class Meta:
        model = User
        fields = ('username','address')

    def create(self, validated_data):
        return User.objects.create(validated_data)

#Serializer for OrderHasProduct
class OrderHasProductSerializer(serializers.ModelSerializer):

    product_id=serializers.IntegerField(source='productid.id')
    order_id=serializers.IntegerField(source='orderid.id')

    productname=serializers.CharField(source='productid.productname',read_only=True)
    code=serializers.CharField(source='productid.code',read_only=True)

    class Meta:
        model=OrderHasProduct
        field = ('id','price','product_id','order_id','quantityordered','productname','code')
    def create(self,validated_data):
        return OrderHasProduct.objects.create(self.validated_data)

    def update(self, instance, validated_data):
        # patch request if partial flag is set to True
        if (self.partial is True):
            #print(validated_data)
            print(validated_data['quantityordered'])
            instance.quantityordered = validated_data['quantityordered']
            instance.save()
            return instance


#Serializer for Categories
class CategoriesSerializer(serializers.ModelSerializer):

    class Meta:
        model = Categories
        fields = ('description','id')

    def create(self, validated_data):
            return User.objects.create(self.validated_data)

#Serializer for Products
class ProductSerializer(serializers.ModelSerializer):

    category = serializers.CharField(source='categoryid.description')
    category_id = serializers.IntegerField(source='categoryid.id',required=False)
    availablequantity=serializers.IntegerField(source='quantity',read_only=True)
    name=serializers.CharField(source='productname',read_only=True)
    #price=serializers.IntegerField(source='price')
    #code=serializers.IntegerField(source='code')
    #description=serializers.CharField(source='description')

    class Meta:
        model = Product
        fields = ('id', 'code', 'description', 'price', 'category','category_id','availablequantity','name')

    def create(self, validated_data):
        categoryobject, created = Categories.objects.get_or_create(
           description=validated_data['categoryid']['description']
        )

        validated_data['categoryid'] =categoryobject
        return Product.objects.create(**validated_data)

    def update(self, instance,validated_data):
        #patch request if partial flag is set to True
        if(self.partial is True):
            if (validated_data.get('categoryid') is not None):
                categoryObject, created = Categories.objects.get_or_create(
                    description=validated_data['categoryid']['description']
                )
                validated_data['categoryid'] = categoryObject
            if (validated_data.get('code') is not None):
                instance.code = validated_data['code']
            if (validated_data.get('description') is not None):
                instance.description = validated_data['description']
            if (validated_data.get('price') is not None):
                instance.price = validated_data['price']
            instance.save()
            return instance

        #put
        if (validated_data.get('categoryid') is not None):
            categoryObject, created = Categories.objects.get_or_create(
                description=validated_data['categoryid']['description']
            )

            validated_data['categoryid'] = categoryObject
        instance.code=validated_data['code']
        instance.description=validated_data['description']
        instance.price=validated_data['price']
        instance.categoryid=validated_data['categoryid']

        instance.save()
        return instance


class OrdersSerializer(serializers.ModelSerializer):
    username = serializers.CharField(source='fkuserid.username',required=False)
    address = serializers.CharField(source='fkuserid.address',required=False)

    class Meta:
        model=Orders
        fields=('id','username','address','status')
    #post request
    def create(self,validated_data):
        if(validated_data.get('fkuserid') is not None):
            if(validated_data['fkuserid'].get('address') is not None):
                user,created=User.objects.get_or_create(
                    username=validated_data['fkuserid']['username'],
                    address=validated_data['fkuserid']['address']
                )
            else:
                user,created=User.objects.get_or_create(
                    username=validated_data['fkuserid']['username'],
                )
            validated_data['fkuserid']=user
        validated_data['status']="CREATED"
        return Orders.objects.create(**validated_data)

    def update(self, instance, validated_data):
        #patch request
        if(self.partial is True):
            if (validated_data.get('fkuserid') is not None):
                if (validated_data.get('fkuserid').get('address') is None):
                    if (instance.fkuserid is not None):
                        user_address = instance.fkuserid.address
                    else:
                        user_address = None
                else:
                    user_address = validated_data['fkuserid']['address']

                print(validated_data['fkuserid']['username'], user_address)
                userObject, created = User.objects.get_or_create(
                    username=validated_data['fkuserid']['username'],
                    address=user_address
                )
                validated_data['fkuserid'] = userObject
                instance.fkuserid=validated_data['fkuserid']
            if(validated_data.get('status') is not None):
                instance.status=validated_data['status']
            instance.save()
            return  instance

        #put request
        if (validated_data.get('fkuserid') is not None):
            if(validated_data.get('fkuserid').get('address') is None):
                if(instance.fkuserid is not None):
                    user_address=instance.fkuserid.address
                else:
                    user_address=None
            else:
                user_address=validated_data['fkuserid']['address']

            print(validated_data['fkuserid']['username'],user_address)
            userObject, created = User.objects.get_or_create(
                username=validated_data['fkuserid']['username'],
                address=user_address
            )

            validated_data['fkuserid'] = userObject
        instance.fkuserid=validated_data['fkuserid']
        instance.status = validated_data['status']

        instance.save()
        return instance