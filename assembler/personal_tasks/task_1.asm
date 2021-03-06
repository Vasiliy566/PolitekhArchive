TITLE Программа "Будильник"
 
;Определение сегмента стека /можно оставлять без изменения/
;Имя сегмента стека - stacksg
StackSg SEGMENT PARA STACK 'Stack'  ;Стандартный заголовок определения стека
    dw 32 dup(?)                ;Задание глубины стека
StackSg ENDS                        ;Конец сегмента стека
 
;Определение сегмента данных
DataSg SEGMENT PARA 'Data'          ;Стандартный заголовок определения данных
    ;Здесь определяются данные
    MINUTES     DW    1     ;хранит число минут до звонка
    ALARMCOUNT  DW    0     ;хранит счетчик времени для звонка
DataSg ENDS      ;Конец сегмента данных
 
;Сегмент кода - тело программы
CodeSg SEGMENT PARA 'Code'          ;Заголовок сегмента кода
    ASSUME cs: CodeSg, ds: DataSg, ss: StackSg
Begin PROC far                      ;Начало программы - основной процедуры
    ;---Пролог EXE-файла---
    push ds
    mov ax,DataSg
    mov ds,ax
    ;---Конец пролога---
 
    ;---Непосредственно программа---
 
;---установка ожидаемого значения счетчика времени суток
    ;CALL  REQUEST_MINUTES  ;запрос числа минут до звонка (1-60)
    MOV   AX,0001;MINUTES   ;пересылка в AX
    MOV   BX,1092           ;число импульсов счетчика в минуте
    MUL   BX                ;умножаем - результат в AX
    push ax
    ;получаем текущее значение счетчика
    MOV   AH,0              ;номер функции чтения счетчика
    INT   1AH               ;читаем значение, младший байт в DX
    ;складываем оба значения
    pop ax
    ADD   AX,DX             ;
    MOV   ALARMCOUNT,AX     ;получаем нужное значение счетчика
 
 
;---заменяем вектор пустого прерывания
    PUSH  DS                ;сохраняем сегмент данных
    MOV   AX,SEG ALARM      ;берем сегмент процедуры ALARM
    MOV   DS,AX             ;помещаем его в DS
    MOV   DX,OFFSET ALARM   ;берем смещение процедуры
    MOV   AL,1CH            ;номер изменяемого вектора
    MOV   AH,25H            ;функция изменения вектора
    INT   21H               ;меняем вектор
    POP   DS                ;восстанавливаем сегмент данных
;
;---дальше продолжается программа
;
 
;---в конце программы возвращаем вектор прерывания
;   MOV   DX,0FF53H         ;оригинальные значения для
;   MOV   AX,0F000H         ;прерывания 1CH
;   MOV   DS,AX             ;помещаем сегмент в DS
;   MOV   AL,1CH            ;номер изменяемого вектора
;   MOV   AH,25H            ;номер функции
;   INT   21H               ;восстанавливаем вектор
    pop cx
    mov dx,cs
    sub dx,cx
    add dx,codsize
    mov ax,3100h
    int 21h
;   ret
Begin ENDP      ;Стандартный конец программы
 
 
;---процедура выдачи звукового сигнала
ALARM PROC FAR          ;создаем длинную процедуру
    push ds
         PUSH AX           ;сохраняем изменяемые регистры
         PUSH CX           ;
         PUSH DX           ;
    mov ax,DataSg
    mov ds,ax
;---читаем счетчик времени суток
         MOV  AH,0         ;номер функции чтения счетчика
         INT  1AH          ;читаем значение счетчика
;---сравниваем с требуемым значением
         MOV  CX,ALARMCOUNT   ;берем требуемое значение
         CMP  DX,CX        ;сравниваем с текущим
         JNE  NOT_YET      ;если неравны, то на выход
;---выдаем звуковой сигнал, если значения совпали
         CALL BEEP         ;эта процедура не показана
;---иначе возвращаемся из прерывания
NOT_YET: POP  DX           ;восстанавливаем регистры
         POP  CX           ;
         POP  AX           ;
    pop ds
         IRET              ;возврат из прерывания
ALARM ENDP              ;конец процедуры
 
;---------------------------Гудок-----------------------------------------
Beep PROC near      ;---гудок динамика
    mov dx,800  ;счетчик числа циклов
    in  al,61h  ;читаем порт B 8255
    and al,0FEH ;выключаем бит таймера 8253
    NextCycle:
        or al,2         ;включаем бит динамика
        out 61h,al  ;посылаем байт в порт B
        mov cx,150  ;длительность первой половины
    CycleUp:
        loop CycleUp    ;задержка пока сигнал высокий
        and al,0FDH ;выключаем бит динамика
        out 61h,al  ;посылаем байт в порт B
    CycleDown:
        loop CycleDown  ;задержка пока сигнал низкий
        dec dx          ;уменьшаем счетчик циклов
        jnz NextCycle   ;повторяем цикл пока DX не 0
    ret
Beep ENDP
codsize=($-Begin)/16+1
;-------------------------------------------------------------------------
CodeSg ENDS     ;
END Begin       ;
