import rest_framework_filters as filters
from . models import *

class OrderHasProductFilter(filters.FilterSet):

    id=filters.NumberFilter(name='id',lookup_type='exact')
    class Meta:
        model = OrderHasProduct
        fields = ['id']