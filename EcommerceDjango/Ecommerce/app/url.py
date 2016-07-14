from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^reports/daily-sale$',views.reports,name='reports')
]
