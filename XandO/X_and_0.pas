// powered by Andrew
// February 4, 2019
program X_and_0;
LABEL m;
var 
  main: array [1..3,1..3] of integer;
  i : integer; 
  j : integer;
  b : boolean;
  x, y : integer;
  function isFillEmpty(x,y : integer): boolean;
  begin
  if ( main[x,y] = 0) then
  isFillEmpty := true
  else
  isFillEmpty := false;
  end;
  procedure showBoard();
var i,j: integer;
begin
 for i := 1 to 3 do begin
   for j := 1 to 3 do begin
     if (main[i,j] = 0) then
       write (' '+ '|');
     if (main[i,j] = 1) then
       write ('X'+ '|');
     if (main[i,j] = 2) then
       write ('O'+ '|');
    end;
    writeln;
     writeln('------');
    end;
    for i := 0 to 2 do
    writeln;
end;
// процедура для очистки поля от крестиков и ноликов
procedure clearBord();
var 
  i : integer; 
  j : integer;
 begin
   for i:= 1 to 3 do 
     for j:= 1 to 3 do 
       main [i,j]:= 0;
 end;
// процедура хода
procedure doMove(x, y : integer;
s: boolean);
begin

if (s = true ) then 
main[x,y]:= 1 ;
 if  (s = false)
then main[x,y]:= 2;

end;
function isSomebodyWon (x,y : integer):integer;
begin
// Check on diagonall
if ((x = y) or (x = 4 - y)) then begin
if ( (x = 2) and (y = 2)) then
	if( ((main[1, 1] = main [2, 2]) and (main [1, 1] = main [3 ,3])) 
	or ( (main[1, 3] = main [2, 2]) and (main [3, 1] = main [3 ,3])) )  then
	isSomebodyWon := main[2,2]
else if ( (main[x , y] = main [2 , 2]) and (main [x , y] = main [y , x] )) then
		isSomebodyWon := main[2,2]
   end
   
else if ((main[x,3] = main[x,2]) and  (main[x,3] = main[x,1]) ) then
   isSomebodyWon := main[x,1]
 else if ((main[1,y] = main[2,y]) and  (main[1,y] = main[3,y]) ) then
   isSomebodyWon := main[1,y]
   else isSomebodyWon := 0;
end;
BEGIN

clearBord;
writeln('We are glad to see you in our Game!');
writeln(' Rules to play our game:');
writeln('1. You should writ coordinates of place you want to do move');
writeln('2. We play for common X and O rules');
writeln('GOOD LUCK AND HAVE FUN!');
for i := 1 to 4 do 
	writeln();
	
for i := 1 to 9 do begin 


if ( (i mod 2) = 0) then begin
	 b := false;
	 writeln( 'O - move');
	 end
else  begin
b := true;
writeln(' X - move');
end;

 m:
 read (x,y);
if ( not(isFillEmpty(x , y)) or  (x > 3) or (y > 3) ) then begin
writeln('wrong coordinates, try agin');
GOTO m;
end;

	
doMove(x,y,b);
if (isSomebodyWon(x,y) = 1) then begin
writeln ('X - win');
showBoard;
break;
end
else if(isSomebodyWon(x,y) = 2) then  begin
writeln ('0 - win');
showBoard;
break;
end;
showBoard;
end;
END.
