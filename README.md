# simple-note-keeper
A really simple personal note keeper

##Different Implementations

* Pure Java with servlet and in memory data registry. API Only
	GET /simple-note-keeper/categories: return all saved categories
	POST /simple-note-keeper/categories: saves a category
		Excpected json: { "name": "Tech" ,  "color" : "black"}
	
	GET /simple-note-keeper/notes: return all saved notes
	POST /simple-note-keeper/notes: saves a note
		Expected json: { "title": "A note" ,  "note" : "This is a note with category!", "category" : {"name":"Tech"}}

### Next Versions:
- Spring boot with in memory database
- Pure Java with Amazon Web Services RDS
- Spring boot with Amazon Web Services RDS