from django.shortcuts import render_to_response
from django.http import Http404

from models import Mission

def ajax_items(request, **kwargs):
    category_id = kwargs.pop("category_id", None)
    if category_id is None:
        raise Http404
        
    profile = request.user.get_profile()        
    completed_missions = profile.completed_missions.all()
    missions = Mission.objects.filter(category=category_id)
    return render_to_response('achievements/missions.html', 
                              {'missions': missions,
                               "completed_missions": completed_missions})