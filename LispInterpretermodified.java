//package compiler;
import java.util.*;
import java.io.*;
public class proj5
{
	static int a=-1;
	static int ef=0;
	static String es="";
	static String[] arr={ "T", "NIL", "CAR", "CDR", "CONS", "ATOM", "EQ", "NULL", "INT", "PLUS", "MINUS", "TIMES","LESS", "GREATER", "COND"};
	static String  s="";
	static int i =0;
	static int t = 0;
	public static List<node> roots=new ArrayList<node>();
	static node dlist=new node("");
	static String tce ="";
	static String el ="";	
	public static void main(String [] args)
	{
		Scanner scan =new Scanner(System.in);
		while(scan.hasNext())
		{
			s=s+scan.nextLine()+"\n";
		}
		
		s=s+"$";
		verify(s);
		while( ef!=1 )
		{  
			String uy=getNextToken(s);
			if(uy.equals("("))
			{
				//a++;
				node root=new node("");
				roots.add(t,root);
				maketree(roots.get(t));
				//prtr(roots.get(t));
				//System.out.println();
				t++;
			}
			else if (uy.equals("$"))
			{
				break;
			}
			else
			{
				node root= new node(uy);
				roots.add(t,root);
				//System.out.println(root.value);
				t++;
				//a++;
			}
		}
		if(ef!=1)
		{
			int r=roots.size();
			for(int j=0;j<r;j++)
			{
				//prtr(roots.get(j));
				//System.out.println();
			}
			
		}
		else
		{
			System.out.println("ERROR: Invalid "+es);
		}
		 //prtr(roots.get(0));
		 
		
			int r=roots.size();
			node fin=null ;
			for(int j=0;j<r;j++)
			{
				//if (j==r)
				//{
					//fin = dlist;
				//}
				//else
			//	{
					fin = roots.get(j);
			//	}
				if(fin.value.equals("T")||fin.value.equals("NIL")||intchk(fin))
				{
					printlst(fin);
					System.out.println();
				
				}
				
				else if(fin.value.equals(""))
				{
					//if(j!=r)
					//{
						
						//System.out.println(checktype(roots.get(j)));
						//System.out.println(evald(roots.get(j)));
						//fin = eval(roots.get(j));
					//}
					//else
					//{
						//fin=dlist;
					//}
					if(!checktype(roots.get(j)).equals("wrong")&&!evald(roots.get(j)).equals("wrong"))
					 {
						 if(fin.value.equals(""))
						 {
						 System.out.print("(");
						 printlst(fin);
						 System.out.print(")");
						 }
						 else
						 {
							 printlst(fin);
						 }
						 //System.out.println();
					}
					else
					{
						if (checktype(roots.get(j)).equals("wrong")){
						System.out.println(tce);
						break;}
						else
						{
							System.out.println(el);
						break;
						}
					}		
					System.out.println();
				}
				else
				{
					System.out.print("undefined-single atom cannot be any thing other than T NIL and an INT");
					break;
				}
				
			
			}
			
			
		 

	}
	public static void maketree(node root)
	{
		if(ef!=1)
		{
		String d = getNextToken(s);
			
			if(d.equals("("))
			{
				//a++;
				root.left=new node("");
				maketree(root.left);
				root.right= new node("");
				maketree(root.right);
			}
			else if(d.equals(")"))
			{
				//a++;
				root.value="NIL";
				return;			
			}
	//			if (s.charAt(i)>43)
			else
			{
				root.left = new node(d);
				//a++;
				root.right=new node("");
				maketree(root.right);
				return;
			}
		}
		else 
		{
			return;
		}
	}
	public static void prtr(node ptr)
	{
		if(ptr!=null)
		{
			if(ptr.right==null&&ptr.left==null)
			{
				System.out.print(ptr.value);
			}
			else
			{
				System.out.print("(");
				prtr(ptr.left);
				System.out.print(" . ");
				prtr(ptr.right);
				System.out.print(")");
			}
		}
	}
	public static String getNextToken(String t)
	{
			a=a+1;
			if(t.charAt(a)=='(')
			{
					return "(";
			}
			else if(t.charAt(a)==')')
				   {
					return ")";
			}
			else if(t.charAt(a)>64 && t.charAt(a)<91)
			{
					int p =a;
					String la="";
					while(t.charAt(p)>43&&t.charAt(p)!='\n'&&t.charAt(p)!='\r')
					{
							la=la+t.charAt(p);
							p++;
					}a=p-1;

					return(la);

			}

			else if(t.charAt(a)>47 && t.charAt(a)<58)
			{
					int p =a;
					String Na="";
					while(t.charAt(p)>43)
					{
							if(t.charAt(p)>60)
							{
									ef=1;
							}
							Na=Na+t.charAt(p);
							p++;
					}a=p-1;
					es="token:"+Na;
					return(Na);

			}

			else if(t.charAt(a)==32||t.charAt(a)==10||t.charAt(a)==13||t.charAt(a)=='\n'||t.charAt(a)=='\r'||t.charAt(a)=='\t'||t.charAt(a)==' ')
			{
					return getNextToken(t);
			}
			else
			{
					return "$";
			}

	}
	public static void verify(String s)
	{
		int i=0;
		int brac=0;
			while(s.charAt(i)!='$')
			{
				if(s.charAt(i)=='(')
				{
					brac++;
					
				}
				else if(s.charAt(i)==')')
				{
					brac--;
					
				}
				
				i++;
			}
			if(brac!=0)
			{
					ef=1;
					es="does not follow grammar";
			//		break;
			}
	}
	public static node rightmost(node n)
	{
		if(n.right!=null)
		{
			return rightmost(n.right);
		}
		else
		{
			return n;
		}
	}
	public static int length(node n)
	{
		if(rightmost(n).value.equals("NIL"))
		{
			if(n.value.equals("NIL"))
			{
				return 0;
			}
			else if(n.right.value.equals("NIL"))
			{
				return 1;
			}
			else
			{
			return 1+length(n.right);
			}
		}
		else
		{
			return (-1);
		}
	}
	public static node car(node fulltree)
	{
		if(!(atom(fulltree)))
		{
		return fulltree.left;
		}
		else
		{
			ef=1;
			es="undefined- Car not defined on singe atom";
			return fulltree;
		}
	}
	public static node quote(node fulltree)
	{
		return fulltree.left;
	}
	public static node cdr(node fulltree)
	{
		if(!(atom(fulltree)))
		{
		return fulltree.right;
		}
		else
		{
			ef=1;
			es="undefined- Cdr not defined on singe atom";
			return fulltree;
		}
	//	return fulltree.right;
	}
	public static node plus(node fulltree)
	{
		if(intchk(eval(car(fulltree))))
		{
			if(intchk(eval(car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(car(fulltree)).value);
				int el2=Integer.parseInt(eval(car(cdr(fulltree))).value);
				int nwt=el1+el2;
				node nw= new node(String.valueOf(nwt));
				return nw;
			}
			else
			{
				ef=1;
				es="undefined: not an integer";
				return(fulltree);

			}
		}
		else
		{
			ef=1;
			es="undefined: not an integer";
			return(fulltree);
		}
	}
	
	public static boolean intchk(node fulltree)
	{
		try { 
			Integer.parseInt(fulltree.value); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	
	public static node less(node fulltree)
	{
		if(intchk(eval(car(fulltree))))
		{
			if(intchk(eval(car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(car(fulltree)).value);
				int el2=Integer.parseInt(eval(car(cdr(fulltree))).value);
				if(el1<el2)
				{
					node nw= new node(String.valueOf("T"));
					return nw;
				}
				else
				{
					node nw= new node(String.valueOf("NIL"));
					return nw;
				}
			}
			else
			{
				ef=1;
				es="undefined: not an integer";
				return(fulltree);
				
			}
		}
		else
		{
			ef=1;
			es="undefined: not an integer";
			return(fulltree);

		}
	}
	
	public static node eq(node fulltree)
	{
		if(atom(eval(car(fulltree))))
		{
			if(atom(eval(car(cdr(fulltree)))))
			{
				String el1=(eval(car(fulltree))).value;
				String el2=(eval(car(cdr(fulltree)))).value;
				if(el1.equals(el2))
				{
					node nw= new node(String.valueOf("T"));
					return nw;
				}
				else
				{
					node nw= new node(String.valueOf("NIL"));
					return nw;
				}
			}
			
			else
			{
				ef=1;
				es="undefined: not an integer";
				return(fulltree);
			}
		}
		else
		{
			ef=1;
			es="undefined: not an integer";
			return(fulltree);
		}
	}
	public static node Null(node fulltree)
	{
	//	if(atom(eval(car(fulltree))))
	//	{
			if((eval(car(fulltree))).value.equals("NIL"))
			{
				node nw= new node(String.valueOf("T"));
				return nw;
			}
			else
			{
				node nw= new node(String.valueOf("NIL"));
				return nw;
			}
	//	}
	//	else
	//	{
	//		ef=1;
	//		es="undefined:not an atom";
	//		return(fulltree);
	//	}
		
	}
	public static boolean atom(node fulltree)
	{
		if(fulltree.left==null&&fulltree.right==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static node cons(node fulltree)
	{
		node nw	= 	new node("");
		nw.left	= 	eval(car(fulltree));
		nw.right=	eval(car(cdr(fulltree)));
		return nw;
	}
	public static node cond(node fulltree)
	{
		int lt= length(fulltree);
		int lgth= length(fulltree.left);
		if (lt>1)
		{
			if(fulltree.left.value.equals("")&&lgth==2)
			{
				node tem = eval(car(fulltree.left));
				if(tem.value.equals("NIL"))
				{
					return cond(fulltree.right);
				}
				else
				{
					return eval(car(cdr(fulltree.left)));
				}
			}
			else
			{
				ef=1;
				es="undefined: invalid length or expression";
				return fulltree;
			}		
		}
		else
		{
			if(fulltree.left.value.equals("")&&lgth==2)
			{
				node tem = eval(car(fulltree.left));
				if(tem.value.equals("NIL"))
				{
					ef=1;
					es="undefined: invalid  expression";
					return fulltree;
				}
				else
				{
					return eval(car(cdr(fulltree.left)));
				}
			}
			else
			{
				ef=1;
				es="undefined: invalid length or expression";
				return fulltree;
			}	
			
		}
	}
	public static String checktype(node fulltree)
	{
		
		if(intchk(fulltree))
		{
			return "n";
		}
		else if(fulltree.value.equals("NIL"))
		{
			return "l";
		}
		else if(fulltree.value.equals("T")||fulltree.value.equals("F"))
		{
			return "b";
		}
		else if(fulltree.value.equals(""))
		{
		if(fulltree.left.value.equals("PLUS")||fulltree.left.value.equals("LESS")||fulltree.left.value.equals("EQ")||fulltree.left.value.equals("CONS"))
		{
			int lgth =length(fulltree);
			if(lgth==3)
			{
			if(fulltree.left.value.equals("PLUS"))
			{
				if(checktype(fulltree.right.left).equals("n"))
				{
					if(checktype(fulltree.right.right.left).equals("n"))
					{
						return "n";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
					
			}
			else if(fulltree.left.value.equals("LESS"))
			{
				if(checktype(fulltree.right.left).equals("n"))
				{
					if(checktype(fulltree.right.right.left).equals("n"))
					{
						return "b";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}	
			else if(fulltree.left.value.equals("CONS"))
			{
				if(checktype(fulltree.right.left).equals("n"))
				{
					if(checktype(fulltree.right.right.left).equals("l"))
					{
						return "l";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}
			else 
			{
				if(checktype(fulltree.right.left).equals("n"))
				{
					if(checktype(fulltree.right.right.left).equals("n"))
					{
						return "b";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}	
			}
			else
			{
				ef=1;
				tce="type checker error: invalid length";
				es="undefined : invalid length";
				return "wrong";
			}
		}
		else if(fulltree.left.value.equals("CAR")||fulltree.left.value.equals("CDR")||fulltree.left.value.equals("ATOM")||fulltree.left.value.equals("INT")||fulltree.left.value.equals("NULL"))
		{
			int lgth =length(fulltree);
			if(lgth==2)
			{
				if(fulltree.left.value.equals("CAR"))
				{
					if(checktype(fulltree.right.left).equals("l"))
					{
						return "n";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				
				}
				else if(fulltree.left.value.equals("CDR"))
				{
					if(checktype(fulltree.right.left).equals("l"))
					{
						return "l";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}	
				}
				else if(fulltree.left.value.equals("INT"))
				{
					if(checktype(fulltree.right.left).equals("n")||checktype(fulltree.right.left).equals("b")||checktype(fulltree.right.left).equals("l"))
					{
						return "b";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else if(fulltree.left.value.equals("ATOM"))
				{
					if(checktype(fulltree.right.left).equals("l")||checktype(fulltree.right.left).equals("b")||checktype(fulltree.right.left).equals("l"))
					{
						return "b";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					if(checktype(fulltree.right.left).equals("l"))
					{
						return "b";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
			}
			else
			{
				ef=1;
				tce="type checker error: invalid length";
				es="undefined : invalid length";
				return "wrong";
			}
		}
		else if(fulltree.left.value.equals("COND"))
		{
			int lngth = length(fulltree);
			if(lngth>1)
			{
			String pl = condl(fulltree.right,"t");
			
			return pl;
			}
			else
			{
				ef=1;
				es="undefined : invalid length";
				tce="tpe checker error: invalid length";
				return "wrong";
			}
		}
		//else if(fulltree.left.value.equals("DEFUN"))
		//{
			//node pl = defun(fulltree.right);
			//fulltree=pl;
			//return fulltree;
		//}
		else
		{
			ef=1;
			es="undefined: INVALID EXPRESSION";
			tce="type check error : invalid operator";
			return "wrong";
		}
		}
		else
		{
			ef=1;
			tce="type checker error: wrong operators";
			es="eval of literal atoms not defined";
			return "wrong";
		}
	
	}
	public static String condl(node fulltree, String type)
	{
		int lt= length(fulltree);
		int lgth= length(fulltree.left);
		if (lt>1)
		{
			if(fulltree.left.value.equals("")&&lgth==2)
			{
				String tem = checktype(car(fulltree.left));
				if(tem.equals("b"))
				{
					if (type.equals("t"))
					{
						type=checktype(car(cdr(fulltree.left)));
						return condl(fulltree.right,type); 
					}
					else if( type.equals(checktype(car(cdr(fulltree.left)))))
					{
						return condl(fulltree.right,type);
					}
					else
					{
						tce="Type checker error:data type donot match";
						return "wrong";
						
					}
				}
				else
				{
					tce="Type checker error:data type donot match";
					return "wrong";
					
				}
			}
			else
			{
				ef=1;
				es="type checker error: invalid length or expression";
				return "wrong";
			}		
		}
		else
		{
			if(fulltree.left.value.equals("")&&lgth==2)
			{
				String tem = checktype(car(fulltree.left));
				if(tem.equals("b"))
				{
					if (type.equals("t"))
					{
						type=checktype(car(cdr(fulltree.left)));
						return type; 
					}
					else if( type.equals(checktype(car(cdr(fulltree.left)))))
					{
						return type;
					}
					else
					{
						tce="Type checker error:data type donot match";
						return "wrong";
						
					}
				}
				else
				{
					tce="Type checker error:data type donot match";
					return "wrong";
				}
			}
			else
			{
				ef=1;
				tce="type checkr error length does not match";
				es="undefined: invalid length or expression";
				return "wrong";
			}	
			
		}
	}
	public static String evald(node fulltree)
	{
		
		if(intchk(fulltree))
		{
			return "an";
		}
		else if(fulltree.value.equals("NIL"))
		{
			return "0";
		}
		else if(fulltree.value.equals("T"))
		{
			return "T";
		}
		else if(fulltree.value.equals("F"))
		{
			return "F";
		}
		else if(fulltree.value.equals(""))
		{
		if(fulltree.left.value.equals("PLUS")||fulltree.left.value.equals("LESS")||fulltree.left.value.equals("EQ")||fulltree.left.value.equals("CONS"))
		{
			int lgth =length(fulltree);
			if(lgth==3)
			{
			if(fulltree.left.value.equals("PLUS"))
			{
				if(evald(fulltree.right.left).equals("an"))
				{
					if(evald(fulltree.right.right.left).equals("an"))
					{
						return "an";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
					
			}
			else if(fulltree.left.value.equals("LESS"))
			{
				if(evald(fulltree.right.left).equals("an"))
				{
					if(evald(fulltree.right.right.left).equals("an"))
					{
						return "ab";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}	
			else if(fulltree.left.value.equals("CONS"))
			{
				if(evald(fulltree.right.left).equals("an"))
				{
					if(isInt(evald(fulltree.right.right.left)))
					{
						if(toInt(evald(fulltree.right.right.left))>=0)
						{
							return toStr(toInt(evald(fulltree.right.right.left))+1);
						}
						else
						{
							el="EMPTY LIST ERROR";
							tce="EMPTY LIST ERROR";
							return "wrong";
						}
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}
			else 
			{
				if(evald(fulltree.right.left).equals("an"))
				{
					if(evald(fulltree.right.right.left).equals("an"))
					{
						return "ab";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					tce="type checker error data types donot match";
					return "wrong";
				}
			}	
			}
			else
			{
				ef=1;
				tce="type checker error: invalid length";
				es="undefined : invalid length";
				return "wrong";
			}
		}
		else if(fulltree.left.value.equals("CAR")||fulltree.left.value.equals("CDR")||fulltree.left.value.equals("ATOM")||fulltree.left.value.equals("INT")||fulltree.left.value.equals("NULL"))
		{
			int lgth =length(fulltree);
			if(lgth==2)
			{
				if(fulltree.left.value.equals("CAR"))
				{
					if(isInt(evald(fulltree.right.left)))
					{
						if(toInt(evald(fulltree.right.left))>=1)
						{
							return "an";
						}
						else
						{
							el="EMPTY LIST ERROR";
							tce="EMPTY LIST ERROR";
							return "wrong";
						}
					}
					else
					{
						
						tce="type checker error data types donot match";
						return "wrong";
					}
				
				}
				else if(fulltree.left.value.equals("CDR"))
				{
					if(isInt(evald(fulltree.right.left)))
					{
						if(toInt(evald(fulltree.right.left))>=1)
						{
							return toStr(toInt(evald(fulltree.right.left))-1);
						}
						else
						{
							el="EMPTY LIST ERROR";
							tce="EMPTY LIST ERROR";
							return "wrong";
						}
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}	
				}
				else if(fulltree.left.value.equals("INT"))
				{
					if(evald(fulltree.right.left).equals("an"))
					{
						return "T";
					}
					else if(evald(fulltree.right.left).equals("ab")||isInt(evald(fulltree.right.left)))
					{
						return "F";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else if(fulltree.left.value.equals("ATOM"))
				{
					if(evald(fulltree.right.left).equals("T")||evald(fulltree.right.left).equals("F")||evald(fulltree.right.left).equals("ab")||evald(fulltree.right.left).equals("an"))
					{
						return "T";
					}
					else if(isInt(evald(fulltree.right.left)))
					{
						return "F";
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
				else
				{
					if(evald(fulltree.right.left).equals("T")||evald(fulltree.right.left).equals("F")||evald(fulltree.right.left).equals("ab")||evald(fulltree.right.left).equals("an"))
					{
						return "F";
					}
					else if(isInt(evald(fulltree.right.left)))
					{
						if(toInt(evald(fulltree.right.left))>=0)
						{
							return "ab";
						}
						else
						{
							return "F";
						}
					}
					else
					{
						tce="type checker error data types donot match";
						return "wrong";
					}
				}
			}
			else
			{
				ef=1;
				tce="type checker error: invalid length";
				es="undefined : invalid length";
				return "wrong";
			}
		}
		else if(fulltree.left.value.equals("COND"))
		{
			int lngth = length(fulltree);
			if(lngth>1)
			{
			String pl = condd(fulltree.right);
			
			return pl;
			}
			else
			{
				ef=1;
				es="undefined : invalid length";
				tce="tpe checker error: invalid length";
				return "wrong";
			}
		}
		//else if(fulltree.left.value.equals("DEFUN"))
		//{
			//node pl = defun(fulltree.right);
			//fulltree=pl;
			//return fulltree;
		//}
		else
		{
			ef=1;
			es="undefined: INVALID EXPRESSION";
			tce="type check error : invalid operator";
			return "wrong";
		}
		}
		else
		{
			ef=1;
			tce="type checker error: wrong operators";
			es="eval of literal atoms not defined";
			return "wrong";
		}
	
	}
	public static String condd(node fulltree)
	{
		if(condf(fulltree)==0)
		{
			while (!fulltree.value.equals("NIL"))
			{
				if(evald(fulltree.left.left).equals("T"))
				{
					return evald(fulltree.left.right.left);
				}
				fulltree=fulltree.right;
			}
			tce ="empty list checker error:only false in cond";
			return "wrong";
		}
		else
		{
			String k;
			if(evald(fulltree.left.right.left).equals("an"))
			{
				return "an";
			}
			else if(isInt(evald(fulltree.left.right.left)))
			{
				k=evald(fulltree.left.right.left);
				while(!fulltree.value.equals("NIL"))
				{
					if(toInt(evald(fulltree.left.right.left))<toInt(k))
					{
						k=evald(fulltree.left.right.left);
					}
					fulltree=fulltree.right;
				}
				return k; 
			}
			else
			{
				k=evald(fulltree.left.right.left);
				while(!fulltree.value.equals("NIL"))
				{
					if(!k.equals(evald(fulltree.left.right.left)))
					{
						return "ab";
					}
					fulltree=fulltree.right;
				}
				return k;
			}
			
		}
	}
	public static int condf(node fulltree)
	{
		if (fulltree.value.equals("NIL"))
		{
			return 0;
		}
		else if(evald(fulltree.left.left).equals("ab"))
		{
			return 1+condf(fulltree.right);
		}
		else return condf(fulltree.right);
	}
	public static node eval(node fulltree)
	{
		if(intchk(fulltree))
		{
			return fulltree;
		}
		else if(fulltree.value.equals("NIL"))
		{
			return fulltree;
		}
		else if(fulltree.value.equals("T"))
		{
			return fulltree;
		}
		else if(fulltree.value.equals(""))
		{
		if(fulltree.left.value.equals("PLUS")||fulltree.left.value.equals("MINUS")||fulltree.left.value.equals("LESS")||fulltree.left.value.equals("TIMES")||fulltree.left.value.equals("GREATER")||fulltree.left.value.equals("EQ")||fulltree.left.value.equals("CONS"))
		{
			int lgth =length(fulltree);
			if(lgth==3)
			{
			if(fulltree.left.value.equals("PLUS"))
			{
				node pl = plus(fulltree.right);
				fulltree=pl;
				return fulltree;
			}
			// else if(fulltree.left.value.equals("MINUS"))
			// {
				// node pl = minus(fulltree.right);
				// fulltree=pl;
				// return fulltree;
			// }	
			else if(fulltree.left.value.equals("LESS"))
			{
				node pl = less(fulltree.right);
				fulltree=pl;
				return fulltree;
			}	
			// else if(fulltree.left.value.equals("GREATER"))
			// {
				// node pl = greater(fulltree.right);
				// fulltree=pl;
				// return fulltree;
			// }	
			// else if(fulltree.left.value.equals("TIMES"))
			// {
				// node pl = times(fulltree.right);
				// fulltree=pl;
				// return fulltree;
			// }
			else if(fulltree.left.value.equals("CONS"))
			{
				node pl = cons(fulltree.right);
				fulltree=pl;
				return fulltree;
			}
			else 
			{
				node pl = eq(fulltree.right);
				fulltree=pl;
				return fulltree;
			}	
			}
			else
			{
				ef=1;
				es="undefined : invalid length";
				return fulltree;
			}
		}
		else if(fulltree.left.value.equals("CAR")||fulltree.left.value.equals("CDR")||fulltree.left.value.equals("ATOM")||fulltree.left.value.equals("INT")||fulltree.left.value.equals("NULL"))
		{
			int lgth =length(fulltree);
			if(lgth==2)
			{
				 if(fulltree.left.value.equals("CAR"))
				{
					node pl = car(eval(fulltree.right.left));
					fulltree=pl;
					return fulltree;
				}
				else if(fulltree.left.value.equals("CDR"))
				{
					node pl = cdr(eval(fulltree.right.left));
					fulltree=pl;
					return fulltree;	
				}
				else if(fulltree.left.value.equals("INT"))
				{
					if(intchk(eval(fulltree.right.left)))
					{
						node pl=new node("T");
						return pl;
					}
					else
					{
						node pl=new node("NIL");
						return pl;
					}
				}
				else if(fulltree.left.value.equals("ATOM"))
				{
					if(atom(eval(fulltree.right.left)))
					{
						node pl=new node("T");
						return pl;
					}
					else
					{
						node pl=new node("NIL");
						return pl;
					}
				}
				else
				{
					node pl = Null(fulltree.right);
					fulltree=pl;
					return fulltree;
				}
			}
			else
			{
				ef=1;
				es="undefined : invalid length";
				return fulltree;
			}
		}
		else if(fulltree.left.value.equals("COND"))
		{
			int lngth = length(fulltree);
			if(lngth>1)
			{
			node pl = cond(fulltree.right);
			fulltree=pl;
			return fulltree;
			}
			else
			{
				ef=1;
				es="undefined : invalid length";
				return fulltree;
			}
		}
		//else if(fulltree.left.value.equals("DEFUN"))
		//{
			//node pl = defun(fulltree.right);
			//fulltree=pl;
			//return fulltree;
		//}
		else
		{
			ef=1;
			es="undefined: INVALID EXPRESSION";
			return fulltree;
		}
		}
		else
		{
			ef=1;
			es="eval of literal atoms not defined";
			return fulltree;
		}
	}
	public static void printlst(node ptr)
	{
		if(ptr!=null/*&!(ptr.value.equals("nil"))*/)
		{
			if(ptr.right==null&&ptr.left==null)
			{
				System.out.print(ptr.value);
			}
			else
			{
				if(ptr.left.value.equals(""))
				{				
					System.out.print("(");
					printlst(ptr.left);
					System.out.print(")");
				}
				else 
				{
					printlst(ptr.left);
				}
				System.out.print(" ");
				{
				if(ptr.right.value.equals("NIL"))
				{
					System.out.print("");
				}
				else if(ptr.right.value.equals(""))
				{
					//System.out.print(" . ");
					printlst(ptr.right);
				}
				else 
				{
					System.out.print(" . ");
					printlst(ptr.right);
				}
				}
			}
		}
	}
	public static boolean isInt(String s) 
	{
		try 
		{ 
			Integer.parseInt(s); 
		} 
		catch(NumberFormatException e) 
		{ 
			return false; 
		} 
		catch(NullPointerException e) 
		{
			return false;
		}
		return true;
	}
	public static int toInt(String s) 
	{
		try 
		{ 
			return Integer.parseInt(s); 
		} 
		catch(NumberFormatException e) 
		{ 
			return -187; 
		} 
		catch(NullPointerException e) 
		{
			return -187;
		}
	}	
	public static String toStr(int i)
	{
		return Integer.toString(i);
	}
	// public static boolean checkkeywords(node a)
	// {
		// String ab=a.value;
		// for (int h=0;h<17;h++)
		// {
			// if(ab.equals(arr[h]))
			// {
				// ef=1;
				// es="matches keywords";
				// return true;
			// }
		// }
		// return false;
	// }
	

}
class node
{
	String value="";
	node right = null;
	node left = null;
	public node( String value )
	{
		this.value=value;	
	}
	  public node getL()
     {
         return left;
     }
     public node getR()
     {
         return right;
     }
}