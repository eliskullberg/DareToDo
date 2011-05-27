from django.conf.urls.defaults import *
from django.views.generic.simple import direct_to_template


urlpatterns = patterns("achievements.views",
    url(r"^ajax_overview$", "ajax_overview"),
    url(r"^ajax_items/(?P<category_id>\d+)$", "ajax_items"),
    url(r"^ajax_register_completed/(?P<mission_id>\d+)$", "ajax_register_completed"),
    url(r"^ajax_undo_completed/(?P<mission_id>\d+)$", "ajax_undo_completed"),
)
