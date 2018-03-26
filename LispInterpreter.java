//package compiler;
import java.util.*;
import java.io.*;
public class proj4
{
	static int a=-1;
	static int ef=0;
	static String es="";
	static String[] arr={ "T", "NIL", "CAR", "CDR", "CONS", "ATOM", "EQ", "NULL", "INT", "PLUS", "MINUS", "TIMES", "LESS", "GREATER", "COND", "QUOTE", "DEFUN"};
	static String  s="";
	static int i =0;
	static int t = 0;
	public static List<node> roots=new ArrayList<node>();
	static node dlist=new node("");
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
						node alist=null;
						fin = eval(alist,roots.get(j));
					//}
					//else
					//{
						//fin=dlist;
					//}
					if(ef!=1)
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
						System.out.println(es);
						break;
					}		
					System.out.println();
				}
				else
				{
					System.out.print("undefined-single atom cannot be any thing other than T NIL and an INT");
					break;
				}
				
			
			}
			//fin =dlist;
			
				// System.out.print("(");
				 //printlst(fin);
				 //System.out.print(")");
			
		 

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
	public static node plus(node alist,node fulltree)
	{
		if(intchk(eval(alist,car(fulltree))))
		{
			if(intchk(eval(alist,car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(alist,car(fulltree)).value);
				int el2=Integer.parseInt(eval(alist,car(cdr(fulltree))).value);
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
	public static node minus (node alist,node fulltree)
	{
		//System.out.println(eval(alist,car(fulltree)).value);
			if(intchk(eval(alist,car(fulltree))))
		{
			if(intchk(eval(alist,car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(alist,car(fulltree)).value);
				int el2=Integer.parseInt(eval(alist,car(cdr(fulltree))).value);
				int nwt=el1-el2;
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

	public static node times(node alist,node fulltree)
	{
		if(intchk(eval(alist,car(fulltree))))
		{
			if(intchk(eval(alist,car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(alist,car(fulltree)).value);
				int el2=Integer.parseInt(eval(alist,car(cdr(fulltree))).value);
				int nwt=el1*el2;
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
	public static node less(node alist,node fulltree)
	{
		if(intchk(eval(alist,car(fulltree))))
		{
			if(intchk(eval(alist,car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(alist,car(fulltree)).value);
				int el2=Integer.parseInt(eval(alist,car(cdr(fulltree))).value);
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
	public static node greater(node alist,node fulltree)
	{
		if(intchk(eval(alist,car(fulltree))))
		{
			if(intchk(eval(alist,car(cdr(fulltree)))))
			{
				int el1=Integer.parseInt(eval(alist,car(fulltree)).value);
				int el2=Integer.parseInt(eval(alist,car(cdr(fulltree))).value);
				if(el1>el2)
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
	public static node eq(node alist,node fulltree)
	{
		if(atom(eval(alist,car(fulltree))))
		{
			if(atom(eval(alist,car(cdr(fulltree)))))
			{
				String el1=(eval(alist,car(fulltree))).value;
				String el2=(eval(alist,car(cdr(fulltree)))).value;
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
	public static node Null(node alist,node fulltree)
	{
	//	if(atom(eval(alist,car(fulltree))))
	//	{
			if((eval(alist,car(fulltree))).value.equals("NIL"))
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
	public static node cons(node alist,node fulltree)
	{
		node nw	= 	new node("");
		nw.left	= 	eval(alist,car(fulltree));
		nw.right=	eval(alist,car(cdr(fulltree)));
		return nw;
	}
	public static node cond(node alist,node fulltree)
	{
		int lt= length(fulltree);
		int lgth= length(fulltree.left);
		if (lt>1)
		{
			if(fulltree.left.value.equals("")&&lgth==2)
			{
				node tem = eval(alist,car(fulltree.left));
				if(tem.value.equals("NIL"))
				{
					return cond(alist,fulltree.right);
				}
				else
				{
					return eval(alist,car(cdr(fulltree.left)));
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
				node tem = eval(alist,car(fulltree.left));
				if(tem.value.equals("NIL"))
				{
					ef=1;
					es="undefined: invalid  expression";
					return fulltree;
				}
				else
				{
					return eval(alist,car(cdr(fulltree.left)));
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
	public static node eval(node alist,node fulltree)
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
				node pl = plus(alist,fulltree.right);
				fulltree=pl;
				return fulltree;
			}
			else if(fulltree.left.value.equals("MINUS"))
			{
				node pl = minus(alist,fulltree.right);
				//printlst(alist);
				fulltree=pl;
				return fulltree;
			}	
			else if(fulltree.left.value.equals("LESS"))
			{
				node pl = less(alist,fulltree.right);
				fulltree=pl;
				return fulltree;
			}	
			else if(fulltree.left.value.equals("GREATER"))
			{
				node pl = greater(alist,fulltree.right);
				fulltree=pl;
				return fulltree;
			}	
			else if(fulltree.left.value.equals("TIMES"))
			{
				node pl = times(alist,fulltree.right);
				fulltree=pl;
				return fulltree;
			}
			else if(fulltree.left.value.equals("CONS"))
			{
				node pl = cons(alist,fulltree.right);
				fulltree=pl;
				return fulltree;
			}
			else 
			{
				node pl = eq(alist,fulltree.right);
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
		else if(fulltree.left.value.equals("CAR")||fulltree.left.value.equals("CDR")||fulltree.left.value.equals("ATOM")||fulltree.left.value.equals("INT")||fulltree.left.value.equals("QUOTE")||fulltree.left.value.equals("NULL"))
		{
			int lgth =length(fulltree);
			if(lgth==2)
			{
				 if(fulltree.left.value.equals("CAR"))
				{
					node pl = car(eval(alist,fulltree.right.left));
					fulltree=pl;
					return fulltree;
				}
				else if(fulltree.left.value.equals("CDR"))
				{
					node pl = cdr(eval(alist,fulltree.right.left));
					//printlst(eval(alist,fulltree.right.left));
					//System.out.println("cdr");
					fulltree=pl;
					return fulltree;	
				}
				else if(fulltree.left.value.equals("QUOTE"))
				{
					node pl = quote(fulltree.right);
					fulltree=pl;
					return fulltree;
				}

				else if(fulltree.left.value.equals("INT"))
				{
					if(intchk(eval(alist,fulltree.right.left)))
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
					if(atom(eval(alist,fulltree.right.left)))
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
					node pl = Null(alist,fulltree.right);
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
			node pl = cond(alist,fulltree.right);
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
		else if(fulltree.left.value.equals("DEFUN"))
		{
			int lngth = length(fulltree);
			if(lngth==4)
			{			
			node pl = defun(fulltree.right);
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
		else if(bound(fulltree.left,dlist))
		{
			node n=getval(fulltree.left,dlist);
			if(chklngth(fulltree.right,n.left))
			{
				alist=addpairs(alist,fulltree.right,n.left);
				//printlst(alist);
				return eval(alist,n.right);
			}
			else
			{
				ef=1;
				es="undefined : invalid actual param size";
				return fulltree;
			}
		}
		else
		{
			ef=1;
			es="undefined: INVALID EXPRESSION";
			return fulltree;
		}
		}
		
		else if(bound(fulltree,alist))	
		{
			
			node n =getval(fulltree, alist);
		//printlst(fulltree);	
		//System.out.println("in eval");
		//printlst(alist);
		//System.out.println("in eval");
		//printlst(getval(fulltree, alist));
		//System.out.println("in eval");
		return n;
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
	public static node defun(node fulltree)
	{
		node temp= dlist;
		while (temp.right!=null && temp.left!= null )
		{
			temp=temp.right;
		}
		node q=car(fulltree);
		//System.out.println((checklist(car(fulltree.right))));
		if((!(checklist(car(fulltree.right))))||checkkeywords(q)||checkrepeats(car(fulltree.right)))
		{
			ef=1;
			es="function name or param list problem";
			return fulltree;
		}
		else
		{
			temp.value="";
			node t=new node("");
			t.left= car(fulltree);
			t.right= new node("");
			t.right.left=car(fulltree.right);
			t.right.right=car(fulltree.right.right);
			temp.left= t;
			temp.right= new node("NIL");
			return t.left;
		}
	}
	
	public static boolean bound(node x,node z)
	{
		boolean ans = false;
		while(!(z.value.equals("NIL")))
		{
			if(z.left.left.value.equals(x.value))
			{
				ans=true;
				
				z=z.right;
				return ans;
			}
			else
			{
				z=z.right;
			}
		}
		//System.out.println(ans);
		return ans;
	}
	public static node getval(node x,node z)
	{
		node ans=null;
		while(!(z.value.equals("NIL")))
		{
			if(z.left.left.value.equals(x.value))
			{
				ans=z.left.right;
				return ans;
				//z=z.right;
			}
			else
			{
				z=z.right;
			}
			
		}
		return ans;
	}
	public static node addpairs(node alist,node ap,node fp)
	{
		node al=new node("NIL");
		node temp=al;
		while(!(ap.value.equals("NIL")))
		{
			temp.value="";
			temp.left=construct(fp.left,eval(alist,ap.left));
			//printlst(eval(alist,ap.left));
			//System.out.println();
			temp.right=new node("NIL");
			ap=ap.right;
			fp=fp.right;
			temp=temp.right;
		}
		while(alist!=null&&!(alist.value.equals("NIL")))
		{
			temp.value="";
			temp.left=alist.left;
			alist=alist.right;
			temp.right=new node("NIL");
			temp=temp.right;
		}
		//printlst(al);
		//System.out.println();
		return al;
	}
	public static void evlist()
	{
		
	}
	public static boolean chklngth(node a, node b)
	{
		if(length(a)==length(b))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static boolean checkkeywords(node a)
	{
		String ab=a.value;
		for (int h=0;h<17;h++)
		{
			if(ab.equals(arr[h]))
			{
				ef=1;
				es="matches keywords";
				return true;
			}
		}
		return false;
	}
	public static boolean checklist(node n)
	{
		if (n.value.equals("NIL")||n.value.equals(""))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public static boolean checkrepeats(node fulltree)
	{
		node temp= fulltree;
		String s=temp.value;
		while(!(temp.value.equals("NIL")))
		{
			//System.out.println(!(checklist(temp.left)));
			if(chk(temp.left, temp.right)||(checklist(temp.left)))
			{
				return true;
			}
			else
			{
				temp=temp.right;
			}
			
		}
		return false;

	}
	public static boolean chk(node x,node y)
	{
		if (checkkeywords(x))
		{
			return true;
		}
		else
		{
			boolean ans = false;
			while(!(y.value.equals("NIL")))
			{
				if(y.left.value.equals(x.value))
				{
					
					y=y.right;
					return true;
				}
				else
				{
					y=y.right;
				}
			}
			return ans;
		}
	}
	public static node construct(node a,node b)
	{
		node k=new node("");
		k.right=b;
		k.left=a;
		return k;
	}
	
	
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