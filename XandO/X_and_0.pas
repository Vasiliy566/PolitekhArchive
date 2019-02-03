// powered by Andrew
// February 4, 2019
program X_and_0;
var 
  main: array [1..3,1..3] of integer;
  i : integer; 
  j : integer;
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
    for i := 0 to 3 do
    writeln;
end;
// процедура для очистки поля от крестиков и ноликов
procedure clearBord();
var 
  b: integer;
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
if (x = y)then begin
 if((main[1,1] = main[2,2]) and  (main[1,1] = main[3,3]) ) then
   isSomebodyWon := main[1,1];
 
if((main[1,3] = main[2,2]) and  (main[3,1] = main[1,3]) ) then
   isSomebodyWon := main[1,3];
   end;
   
end;
BEGIN

showBoard;
doMove(1,1,false);
doMove(2,2,false);
doMove(3,3,false);

showBoard;

writeln(isSomebodyWon(3,3));

END.