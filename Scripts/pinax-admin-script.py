#!"c:\Users\Daniel\My Documents\achievement_tracker\mysite-env\Scripts\python.exe"
# EASY-INSTALL-ENTRY-SCRIPT: 'Pinax==0.9a1','console_scripts','pinax-admin'
__requires__ = 'Pinax==0.9a1'
import sys
from pkg_resources import load_entry_point

sys.exit(
   load_entry_point('Pinax==0.9a1', 'console_scripts', 'pinax-admin')()
)
