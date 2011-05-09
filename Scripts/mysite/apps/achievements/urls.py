from django.conf.urls.defaults import *
from django.views.generic.simple import direct_to_template


urlpatterns = patterns("achievements.views",
    url(r"^ajax_items/(?P<category_id>\d+)$", "ajax_items"),
)
