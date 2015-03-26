# activity_tracker
Lughnasadh Activity tracker

Stuff To Do
===========

1. Move all utility functions into trkUtils class
   Generate UID
   Get working directory
   Build a new DOM node
2. Update jTable to include "dirty" flag
3. Shift event handling from a table model listener to the "Commit" button
4. Add table model build/refresh function that takes List of trkActivity objects
5. Call that function on initial trkConfigurationTable load, and if Cancel button clicked
6. Commit button should build an array of new/changed trkActivity objects
7. Commit button should build an array of deleted activity IDs
8. Need separate sets of buttons for activities and tasks
   Two sets of config data (in same XML file)
   Two panels and two button groups
   Change activity button will need to scan both sets of buttons to see what was selected to construct output
9. Don't try to make it too generic! (at the moment...)
