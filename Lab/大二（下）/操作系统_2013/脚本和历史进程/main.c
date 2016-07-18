#include<stdio.h>
#include<unistd.h>
#include<string.h>
#include<stdlib.h>
#include<signal.h>
#define MAX_LINE 80

char buffer[MAX_LINE];//input
char *args[MAX_LINE/2+1]={0};
char history[10][20]={0};//sava command
int lasthistory=-1;//sign where is the recent command


void setup(char inputBuffer[],char *args[],int *background,int *should_run){//deal with the input
	if(*inputBuffer==0){return;}
	else if(strcmp(inputBuffer,"history")==0){//input is "history"
		if(lasthistory!=-1){/*there are commands*/
			int n;
			for(n=0;n<=9;n++){/*print all commands*/
				if(history[n][0]==0)
					break;
				printf("[%s],",history[n]);
			}
		}
		else/*there is not command*/
			printf("There is not command!!");
	}
	else if(strcmp(inputBuffer,"!!")==0){//input is "!!"
		if(lasthistory==-1){
			printf("There is not recent command!");
		}
		else{
			printf("The recent command is:%s\n",history[lasthistory]);
			int i=0;
			char *part;
			char *com;
			com=malloc(80);
			strcpy(com,history[lasthistory]);
			part=strtok(com," ");/*recent command*/
			while(part){
				if(*part=='&'){
					*background=1;
					part=strtok(NULL," ");
					continue;
				}
				args[i]=part;
				i++;
				part=strtok(NULL," ");
			}
		}
	}
	else if(strlen(inputBuffer)==2 && inputBuffer[0]=='!' && inputBuffer[1]>='0' && inputBuffer[1]<='9'){//input is "!N"
		int th=(int)inputBuffer[1]-48;
		if(history[th][0]!=0){/*No.(n) has command*/
			printf("No.%d command is:%s\n",th,history[th]);
			int i=0;
			char *part;
			char *com;
			com=malloc(80);
			strcpy(com,history[th]);
			part=strtok(com," ");/*No.(n) command*/
			while(part){
				if(*part=='&'){
					*background=1;
					part=strtok(NULL," ");
					continue;
				}
				args[i]=part;
				i++;
				part=strtok(NULL," ");
			} 
		}
		else{/*No.(n) has not command*/
			printf("No.%d has not command!",th);
		}
	}
	else if(strcmp(inputBuffer,"exit")==0){//input is "exit"
		*should_run=0;
	}
	else{//input is a command
		int inputlength;
		inputlength=strlen(inputBuffer);
		if(inputBuffer[inputlength-1]=='&')/*if command+&*/
			*background=1;
		else
			*background=0;

		if(lasthistory<9){/*save command*/
			strcpy(history[lasthistory+1],inputBuffer);
			lasthistory++;
		}
		else{
			strcpy(history[0],inputBuffer);
			lasthistory=0;
		}

		int i=0;/*add args*/
		char *part;
		part=strtok(inputBuffer," ");
		while(part){
			if(*part=='&'){
				part=strtok(NULL," ");
				continue;
			}
			args[i]=part;
			i++;
			part=strtok(NULL," ");
		}
	}
	fflush(stdout);
}

void handler_SIGINT(){//signal
	write(STDOUT_FILENO,buffer,strlen(buffer));
	printf("\n");
	if(lasthistory!=-1){/*there are commands*/
		int n;
		for(n=0;n<=9;n++){/*print all commands*/
			if(history[n][0]==0)
				break;
			printf("[%s],",history[n]);
		}
	}
	else/*there is not command*/
		printf("There is not command!!");
}

int main(void)
{
	struct sigaction handler;//signal
	handler.sa_handler=handler_SIGINT;
	sigaction(SIGINT,&handler,NULL);

	int background;
	int should_run=1;
	printf("**********************Need To Know**********************");
	printf("\n***1.command+&:Child processes will run concurrently;***");
	printf("\n***2. history :Print recent ten commands;            ***");
	printf("\n***3. ctrl+c  :Print recent ten commands;            ***");
	printf("\n***4.   !!    :Execut recent command;                ***");
	printf("\n***5.   !N    :Execut No.(N) command;                ***");
	printf("\n***6.  exit   :Exit the program;                     ***");
	printf("\n********************************************************");
	while(should_run){
		int i;
		for(i=0;i<MAX_LINE/2+1;i++){
			args[i]=0;
		}
		*buffer=0;
		background=0;
		printf("\nosh>");
		fflush(stdout);
		scanf(" %[^\n]",buffer);
		setup(buffer,args,&background,&should_run);
		pid_t pid=fork();
		if(pid<0){
			printf("Fork failed!\n");
		}
		else if(pid==0){
			execvp(args[0],args);
		}
		else{
			if(background==0){
				wait(NULL);
			}
		}
	}
	return 0;
}
