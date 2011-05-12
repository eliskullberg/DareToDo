from django.contrib.auth.decorators import login_required
from django.shortcuts import render_to_response
from django.http import Http404

from models import Mission
from models import UserAchievements

def get_or_create_achievements(user):
    try:
        achievements = UserAchievements.objects.get(user=user)
    except UserAchievements.DoesNotExist:
        achievements = UserAchievements(user=user)
        achievements.save()
    return achievements

@login_required
def ajax_items(request, **kwargs):
    category_id = kwargs.pop("category_id", None)
    if category_id is None:
        raise Http404

    achievements = get_or_create_achievements(request.user)
    completed_missions = achievements.completed_missions.all()
    missions = Mission.objects.filter(category=category_id)
    return render_to_response('achievements/missions.html', 
                              {'missions': missions,
                               "completed_missions": completed_missions})
                               
@login_required
def ajax_register_completed(request, **kwargs):
    mission_id = kwargs.pop("mission_id", None)
    if mission_id is None:
        raise Http404

    achievements = get_or_create_achievements(request.user)
    
    mission = Mission.objects.get(id=mission_id)    
    achievements.completed_missions.add(mission)
    
    return render_to_response('achievements/_completed_mission.html', 
                              {'mission': mission})                               

                              
@login_required
def ajax_undo_completed(request, **kwargs):
    mission_id = kwargs.pop("mission_id", None)
    if mission_id is None:
        raise Http404

    achievements = get_or_create_achievements(request.user)
    
    mission = Mission.objects.get(id=mission_id)    
    achievements.completed_missions.remove(mission)
    
    return render_to_response('achievements/_mission.html', 
                              {'mission': mission})                                                             