from django.conf.urls import url
from rest_framework.routers import DefaultRouter
from . import views
from . import viewsets
from django.conf.urls import include,url

router = DefaultRouter()

router.register(r'orders/(?P<orderid>[0-9]+)/orderlineitem',viewsets.OrderHasProductViewSet,base_name='OrderHasProductViewSet')

router.register(r'products$', viewsets.ProductViewSet,base_name='ProductViewSet')
router.register(r'orders$', viewsets.OrdersViewSet,base_name='OrdersViewSet')
router.register(r'users', viewsets.UserViewSet,base_name='UserViewSet')

urlpatterns = [
    url(r'^orders/summary',views.ordersummary,name='ordersummary'),

    url(r'^reports/daily-sale$',views.reports,name='reports'),
    url(r'^',include(router.urls)),
    url(r'^docs/',include('rest_framework_swagger.urls')),
    url(r'^products/summary',views.productsummary,name='productsummary'),

]
