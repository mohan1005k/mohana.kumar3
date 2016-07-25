import understand
db = understand.open("/var/app/testdb/test_sonic.udb")
methods=db.ents("Methods")

run_methods=[]
for m in methods:
	if(m.name().endswith("run")):
		run_methods.append(m)

for run_method in run_methods:

	#if original source files are present, lexer can be accessed to identify anonymous functions

	#print(run_method.name())
	#print(run_method.ents('ent')[len(run_method.ents('ent'))-1].lexer())
	# class_lexer=run_method.ents('ent')[len(run_method.ents('ent'))-1].lexer()
	# prev=None
	# for iter in class_lexer.__iter__():

	# 	if(iter.ent()==run_method):
	# 		#print('found match :',run_method)
	# 		if((prev is not None) and (prev.type()=="Thread")):
	# 			print('Bug found for Thread.run() without overriding')
	# 	if(iter.ent() is not None):
	# 		prev=iter.ent()

		
	defineIn=run_method.refs("Definin")[0].ent()

	#either implements runnable or extends thread
	if(defineIn is not None):
		for ref in defineIn.refs('Implement'):
		 	if(ref.ent().name()=="Runnable"):
		 		print("Bug found for runnable.run()");
		for ref in defineIn.refs('Extend'):
		 	if(ref.ent().name()=="Thread"):
		 		print("Bug found for thread.run() with overriding")



