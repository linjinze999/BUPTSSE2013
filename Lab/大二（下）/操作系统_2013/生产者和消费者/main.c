#include<pthread.h>
#include<stdio.h>
#include<malloc.h>
#include<unistd.h>

typedef int buffer_item;
#define BUFFER_SIZE 5

buffer_item buffer[BUFFER_SIZE]={0};
int bufferNum=0;

pthread_mutex_t mutex;
pthread_cond_t full=0;
pthread_cond_t empty=5;

int insert_item(buffer_item item){//by producer
	if(bufferNum<BUFFER_SIZE){//success
		buffer[bufferNum]=item;
		bufferNum+=1;
		return 1;
	}
	else//fail
		return 0;
}
int remove_item(buffer_item *item){//by consumer
	buffer_item con;
	if(bufferNum>0){//success
		bufferNum-=1;
		con=item[bufferNum];
		item[bufferNum]=0;
		return con;
	}
	else//fail
		return 0;
}
void *producer(void *param){//producer
	buffer_item rands;
	int sleeptime;
	while(1){
		sleeptime=rand()%100000;
		usleep(sleeptime);//sleep
		rands=rand();
		pthread_mutex_lock(&mutex);//lock
		pthread_cond_wait(&empty,&mutex);//wait
		if(insert_item(rands)){//insert
			printf("Producer produced:%d\n",rands);
			pthread_cond_signal(&full);//signal
		}
		pthread_mutex_unlock(&mutex);//unlock
		
	}
}
void *consumer(void *param){//consumer
	int sleeptime;
	buffer_item con;
	while(1){
		sleeptime=rand()%100000;
		usleep(sleeptime);//sleep
		pthread_mutex_lock(&mutex);//lock
		pthread_cond_wait(&full,&mutex);//wait
		con=remove_item(buffer);//remove
		if(con){
			printf("Consumer consumed:%d\n",con);
			pthread_cond_signal(&empty);//signal
		}
		pthread_mutex_unlock(&mutex);//unlock
	}
}

int main(void){
	pthread_mutex_init(&mutex,NULL);

	pthread_t tid1;
	pthread_t tid2;
	pthread_attr_t attr1;
	pthread_attr_t attr2;

	pthread_attr_init(&attr1);
	pthread_attr_init(&attr2);
	pthread_create(&tid1,&attr1,producer,NULL);//producer
	pthread_create(&tid2,&attr2,consumer,NULL);//consumer
	sleep(2);//Running time
	return 1;
}
