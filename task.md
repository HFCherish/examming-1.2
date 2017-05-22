#tasks
# /employees
* post
	* return 201 when create
	* try to save to db and can get that one from db;
	* contains url in location; --20

	* return 400 when field empty; --15
* get
	* return 200 when get all
	* try to get from db and can get all details -- 20

# /employees/eid
* get
	* return 200 when get one
	* contains right details in response;  --15

	* return 404 when not exists --1
* update
 	* return 204 when update --15

	* return 400 when field empty --5
* delete
 	* return 204 when update --10

# /attendances
* post
	* return 201 when create
	* try to save to db and can get that one from db;
	* contains url in location; --20

	* return 400 when field empty; --15
* get
	* return 200 when get all by filter
	* try to get from db and can get all details -- 20


# /attendances/aid
* get
	* return 200 when get one
	* contains right details in response;  --15

	* return 404 when not exists --1
* update
 	* return 204 when update --15

	* return 400 when field empty --5
* delete
 	* return 204 when update --10
