#include "disk.h"
#include<iostream>
#include<time.h>
#define MAX_FILE 64
#define MAX_FILE_DSECRIPTOR 32
#define END_FILE "END_FILE"
#define SUPER_BLOCKS 11
#define FCB_BEGIN 11
#define DATA_BEGIN 15
#define MIN(a,b)   (a)<(b)?(a):(b)
typedef struct FCB{
	char FileName[15];
	int DataLocation;
	int length;
	char date[8];
	char time[8];
};

class FileSystem {
public:
	int make_fs(char *disk_name){
		if(DISK.make_disk(disk_name)==-1){//创建DISK
			fprintf(stderr, "make_fs: make_disk fail\n");
			return -1;
		};
		if(DISK.open_disk(disk_name)==-1){//打开DISK
			fprintf(stderr, "make_fs: open_disk fail\n");
			return -1;
		};
		int error=0;/*用于记录创建文件系统时是否发生错误*/

		//0~1块用于记录——块是否空闲
		memset(buffer, 0, BLOCK_SIZE);
		memset(buffer, 1, SUPER_BLOCKS+1);/*前12个块已被使用*/
		error=MIN(error,DISK.block_write(0,buffer));
		memset(buffer, 0, BLOCK_SIZE);
		error=MIN(error,DISK.block_write(1,buffer));

		//2~9块记录块的下一个块的位置
		memset(buffer, 0, BLOCK_SIZE);
		buffer[1*sizeof(int)-1]='1';
		for(int n=3;n<10;n++)
			buffer[n*sizeof(int)-1]=n;
		error=MIN(error,DISK.block_write(2,buffer));/*3~9块里面数据已经为0，不再写入*/

		//第10块分别记录64个文件的存在情况、记录FCB信息的块的位置、数据块开始的位置
		memset(buffer, 0, BLOCK_SIZE);/*文件存在情况默认为否0*/
		itoa(FCB_BEGIN,IntAndString,10);
		strcpy(buffer+MAX_FILE,IntAndString);
		itoa(DATA_BEGIN,IntAndString,10);
		strcpy(buffer+MAX_FILE+sizeof(int),IntAndString);
		error=MIN(error,DISK.block_write(10,buffer));

		if(error==-1){/*创建文件系统时发生错误*/
			fprintf(stderr, "make_fs: make_fs fail\n");
			return -1;
		}
		else
			return 0;
	};
	int mount_fs(char *disk_name){
		if(DISK.open_disk(disk_name)==0){//打开DISK
			int error=0;/*用于记录以下程序是否发生错误*/

			//得到第0~1块里面块空闲记录
			error=MIN(error,DISK.block_read(0,buffer));
			for(int n=0;n<BLOCK_SIZE;n++)
				DataFree[n]=buffer[n];
			error=MIN(error,DISK.block_read(1,buffer));
			for(int n=0;n<BLOCK_SIZE;n++)
				DataFree[BLOCK_SIZE+n]=buffer[n];

			//得到第2~9块里面块的下一个块编号
			for(int i=2;i<10;i++){
				error=MIN(error,DISK.block_read(i,buffer));
				for(int n=0;n<BLOCK_SIZE/sizeof(int);n++){
					strncpy(IntAndString,buffer+n*sizeof(int),sizeof(int));
					NextBlock[(i-2)*BLOCK_SIZE/sizeof(int)+n]=atoi(IntAndString);
				}
			}

			//得到第10块里面文件的存在情况、记录FCB信息的块的位置、数据块开始的位置
			error=MIN(error,DISK.block_read(10,buffer));
			for(int n=0;n<MAX_FILE;n++)
				FileExist[n]=buffer[n];
			strncpy(IntAndString,buffer+MAX_FILE,sizeof(int));
			FCBBegin=atoi(IntAndString);
			strncpy(IntAndString,buffer+MAX_FILE+sizeof(int),sizeof(int));
			DataBegin=atoi(IntAndString);

			//得到第11块里面FCB的信息
			error=MIN(error,DISK.block_read(11,buffer));
			for(int n=0;n<MAX_FILE;n++){
				strncpy(fcb[n].FileName,buffer+n*sizeof(FCB),15);
				strncpy(IntAndString,buffer+n*sizeof(FCB)+15,sizeof(int));
				fcb[n].DataLocation=atoi(IntAndString);
				strncpy(IntAndString,buffer+n*sizeof(FCB)+15+sizeof(int),sizeof(int));
				fcb[n].length=atoi(IntAndString);
				strncpy(fcb[n].date,buffer+n*sizeof(FCB)+15+2*sizeof(int),8);
				strncpy(fcb[n].time,buffer+n*sizeof(FCB)+23+2*sizeof(int),8);
			}
			FileDescriptor[0]=0;//FileDescriptor[0]用于记录此时有多少打开文件
			strcpy(DiskName,disk_name);//得到磁盘名
			memset( DataChange,0,DISK_BLOCKS);//用于记录哪些数据块被修改
			for(int n=DataBegin;n<DISK_BLOCKS;n++)//数据块被修改的具体内容
				memset( ChangeData+n*BLOCK_SIZE,0,BLOCK_SIZE);
			if(error==-1){//程序运行时有错
				fprintf(stderr, "mount_fs: mount_fs fail\n");
				return -1;
			}
			else
				return 0;
		}
		else{
			perror("open_disk: cannot open file");
			return -1;
		}
	};
	int umount_fs(char *disk_name){
		int error=0;
		//将块空闲记录写入第0~1块
		for(int n=0;n<BLOCK_SIZE;n++)
			buffer[n]=DataFree[n];
		error=MIN(error,DISK.block_write(0,buffer));
		for(int n=0;n<BLOCK_SIZE;n++)
			buffer[n]=DataFree[BLOCK_SIZE+n];
		error=MIN(error,DISK.block_write(1,buffer));

		//将块的下一个块编号写入第2~9块
		for(int i=2;i<10;i++){
			for(int n=0;n<BLOCK_SIZE/sizeof(int);n++){
				itoa(NextBlock[(i-2)*(BLOCK_SIZE/sizeof(int))+n],IntAndString,10);
				strcpy(buffer+n*sizeof(int),IntAndString);
			}
			error=MIN(error,DISK.block_write(i,buffer));
		}

		//将文件的存在情况、记录FCB信息的块的位置、数据块开始的位置写入第10个块
		memset(buffer, 0, BLOCK_SIZE);
		for(int n=0;n<MAX_FILE;n++)
			buffer[n]=FileExist[n];
		itoa(FCBBegin,IntAndString,10);
		strcpy(buffer+MAX_FILE,IntAndString);
		itoa(DataBegin,IntAndString,10);
		strcpy(buffer+MAX_FILE+sizeof(int),IntAndString);
		error=MIN(error,DISK.block_write(10,buffer));

		//将FCB信息写入第11块
		memset(buffer, 0, BLOCK_SIZE);
		for(int n=0;n<MAX_FILE;n++){
			strncpy(buffer+n*sizeof(FCB),fcb[n].FileName,15);
			itoa(fcb[n].DataLocation,IntAndString,10);
			strcpy(buffer+n*sizeof(FCB)+15,IntAndString);
			itoa(fcb[n].length,IntAndString,10);
			strcpy(buffer+n*sizeof(FCB)+15+sizeof(int),IntAndString);
			strncpy(buffer+n*sizeof(FCB)+15+2*sizeof(int),fcb[n].date,8);
			strncpy(buffer+n*sizeof(FCB)+23+2*sizeof(int),fcb[n].time,8);
		}
		error=MIN(error,DISK.block_write(11,buffer));

		for(int n=DataBegin;n<DISK_BLOCKS;n++)//将被修改的数据块写入磁盘
			if(DataChange[n])
				DISK.block_write(n,ChangeData[n]);

		if(error==-1){//程序运行时有错
			fprintf(stderr, "unmount_fs: unmount_fs fail\n");
			return -1;
		}
		else if(DISK.close_disk()!=0){
			fprintf(stderr, "unmount_fs: close_disk fail\n");
			return -1;
		}
		else
			return 0;
	};

	int fs_open(char *name){
		if (!name||strlen(name)>15) {
			fprintf(stderr, "fs_open: unnormal file name \n");
			return -1;
		}
		for(int n=0;n<MAX_FILE;n++){//寻找文件名是否存在
			if(FileExist[n]&&strcmp(fcb[n].FileName,name)==0){
				if(FileDescriptor[0]==MAX_FILE_DSECRIPTOR){//打开文件个数已最大
					fprintf(stderr, "fs_open: reach opening file count limit\n");
					return -1;
				}
				else{//打开文件成功
					FileDescriptor[0]++;
					FileDescriptor[FileDescriptor[0]]=n;
					FileDescriptor[FileDescriptor[0]]=open(DiskName,fcb[n].DataLocation*BLOCK_SIZE);
					return FileDescriptor[0];
				}
			}
		}
		fprintf(stderr, "fs_open: can not find the FileName\n");
		return -1;
	};
	int fs_close(int fildes){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, "fs_close: fildes out of bounds\n");
			return -1;
		}
		else{
			close(FileDescriptor[fildes]);
			FileDescriptor[FileDescriptor[0]]--;
			return 0;
		}

	};
	int fs_create(char *name){
		if (!name||strlen(name)>15) {//文件名错误
			fprintf(stderr, "fs_create: unnormal file name \n");
			return -1;
		}
		for(int n=0;n<MAX_FILE;n++){//文件名已存在
			if(FileExist[n]&&strcpy(fcb[n].FileName,name)==0){
				fprintf(stderr, "fs_create: the file with name already exists\n");
				return -1;
			}
		}
		for(int n=0;n<MAX_FILE;n++){
			if(!FileExist[n]){//检索文件是否已满64个
				for(int i=DATA_BEGIN;i<DISK_BLOCKS;i++)
					if(!DataFree[i]){//检索是否还有空闲数据块可供文件使用
						strcpy(fcb[n].FileName,name);
						fcb[n].length=0;
						fcb[n].DataLocation=i;
						DataFree[i]=1;
						DataChange[i]=1;
						memset(ChangeData[i],0,BLOCK_SIZE);
						strcpy(ChangeData[i],END_FILE);
						time_t t=time(NULL);
						strftime( fcb[n].date, 8, "%Y/%m/%d",localtime(&t) ); 
						strftime( fcb[n].time, 8, "%H/%M/%S",localtime(&t) );
						FileExist[n]=1;
						int op=fs_open(name);
						if(op==-1)
							fprintf(stderr, "fs_create: fs_open fail!\n");
						return 0;
					}
					fprintf(stderr, "fs_create: block full\n");
					return -1;
			}
		}

	};
	int fs_delete(char *name){
		if (!name||strlen(name)>15) {//文件名错误
			fprintf(stderr, "fs_delete: unnormal file name \n");
			return -1;
		}
		for(int n=0;n<MAX_FILE;n++){
			if(FileExist[n]&&strcmp(fcb[n].FileName,name)){//检索文件是否存在
				for(int i=0;i<FileDescriptor[0];i++)
					if(FileDescriptor[i]==n){
						fprintf(stderr, "fs_delete: the file has been opened\n");
						return -1;
					}
					do{
						DataChange[fcb[n].DataLocation]=1;
						memset(ChangeData[fcb[n].DataLocation],0,BLOCK_SIZE);
						int next=NextBlock[fcb[n].DataLocation];
						NextBlock[fcb[n].DataLocation]=0;
						fcb[n].DataLocation=next;
					}while(NextBlock[fcb[n].DataLocation]!=0&&fcb[n].DataLocation!=0);
					fcb[n].DataLocation=0;
					fcb[n].length=0;
					memset(fcb[n].FileName,0,15);
					memset(fcb[n].date,0,8);
					memset(fcb[n].time,0,8);
					FileExist[n]=0;
					return 0;
			}
		}
		fprintf(stderr, "fs_delete: can not find the FileName\n");
		return -1;
	};
	int fs_read(int fildes, char *buf, size_t nbyte){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, "fs_read: fildes out of bounds\n");
			return -1;
		}
		else if(nbyte<0){//所读大小错误
			fprintf(stderr, "fs_read: nbyte smaller than 0\n");
			return -1;
		}
		else{
			if(fcb[FileDescriptor[fildes]].length==0)
				return 0;
			if(fcb[FileDescriptor[fildes]].length<=nbyte){//文件大小小于length
				int NO=fcb[FileDescriptor[fildes]].DataLocation;
				for(int n=0;n<=fcb[FileDescriptor[fildes]].length/BLOCK_SIZE;n++){
					if(n==fcb[FileDescriptor[fildes]].length/BLOCK_SIZE){
						int size=fcb[FileDescriptor[fildes]].length%BLOCK_SIZE;
						DISK.block_read(NO,buffer);
						strncpy(buf+n*BLOCK_SIZE,buffer,size);
					}
					else{
						DISK.block_read(NO,buffer);
						strcpy(buf+n*BLOCK_SIZE,buffer);
					}
					NO=NextBlock[NO];
				}
				return fcb[FileDescriptor[fildes]].length;
			}
			else{//文件大小大于length
				int NO=fcb[FileDescriptor[fildes]].DataLocation;
				for(int n=0;n<=nbyte/BLOCK_SIZE;n++){
					if(n==nbyte/BLOCK_SIZE){
						int size=nbyte%BLOCK_SIZE;
						DISK.block_read(NO,buffer);
						strncpy(buf+n*BLOCK_SIZE,buffer,size);
					}
					else{
						DISK.block_read(NO,buffer);
						strcpy(buf+n*BLOCK_SIZE,buffer);
					}
					NO=NextBlock[NO];
				}
				return nbyte;
			}
		}
	};
	int fs_write(int fildes, char *buf, size_t nbyte){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, "fs_write: fildes out of bounds\n");
			return -1;
		}
		if(nbyte<0){//所写大小错误
			fprintf(stderr, "fs_write: nbyte smaller than 0\n");
			return -1;
		}
		if(strlen(buf)<nbyte){//所提供的字符串小于要写的大小
			fprintf(stderr, "fs_write:buffer  smaller than nbyte\n");
			return -1;
		}
		int next=0;
		next=fcb[FileDescriptor[fildes]].DataLocation;
		while(NextBlock[next]!=0){
			next=NextBlock[next];
		}
		DataChange[next]=1;
		DISK.block_read(next,ChangeData[next]);
		int left=strlen(ChangeData[next]+fcb[FileDescriptor[fildes]].length/BLOCK_SIZE);
		if(nbyte>left){
			strncpy(ChangeData[next]+fcb[FileDescriptor[fildes]].length%BLOCK_SIZE,buf,left);
			int hascpy=left;
			int n=DATA_BEGIN;
			while(nbyte-hascpy>BLOCK_SIZE){
				for(;n<DISK_BLOCKS;n++)
					if(!DataFree[n]){
						DataChange[n]=1;
						strncpy(ChangeData[n],buf+hascpy,BLOCK_SIZE);
						hascpy+=BLOCK_SIZE;
						NextBlock[next]=n;
						next=n;
						break;
					}
					if(n==DISK_BLOCKS){
						fcb[FileDescriptor[fildes]].length+=hascpy;
						return hascpy;
					}
			}
			for(;n<DISK_BLOCKS;n++)
				if(!DataFree[n]){
					DataChange[n]=1;
					memset(ChangeData[n],0,BLOCK_SIZE);
					strncpy(ChangeData[n],buf+hascpy,nbyte-hascpy);
					NextBlock[next]=n;

					fcb[FileDescriptor[fildes]].length+=nbyte;
					return nbyte;

				}
		}
		else{
			strncpy(ChangeData[next]+fcb[FileDescriptor[fildes]].length%BLOCK_SIZE,buf,nbyte);
			return nbyte;
		}
	};
	int fs_get_filesize(int fildes){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, "fs_get_filesize: fildes out of bounds\n");
			return -1;
		}
		return fcb[FileDescriptor[fildes]].length;
	};
	int fs_lseek(int fildes, off_t offset){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, " fs_lseek: fildes out of bounds\n");
			return -1;
		}
		if(offset>fcb[FileDescriptor[fildes]].length||offset<0){//位移指针错误
			fprintf(stderr, " fs_lseek: offset out of bounds\n");
			return -1;
		}
		FileDescriptor[fildes]=open(DiskName,fcb[FileDescriptor[fildes]].DataLocation*BLOCK_SIZE+offset);
	};
	int fs_truncate(int fildes, off_t length){
		if(FileDescriptor[0]<fildes || fildes<1){//文件标识符错误
			fprintf(stderr, " fs_truncate: fildes out of bounds\n");
			return -1;
		}
		if(length<0){//所截大小错误
			fprintf(stderr, " fs_truncate: length smaller than 0\n");
			return -1;
		}
		if(length<fcb[FileDescriptor[fildes]].length){//所截大小 小于文件大小
			int next=fcb[FileDescriptor[fildes]].DataLocation;
			int hasget=0,next1;
			hasget+=BLOCK_SIZE;
			while(hasget<length){
				next=NextBlock[next];
				hasget+=BLOCK_SIZE;
			}
			DataChange[next]=1;
			memset(ChangeData[next]+length%BLOCK_SIZE,0,BLOCK_SIZE-length%BLOCK_SIZE);
			next1=next;
			next=NextBlock[next];
			NextBlock[next1]=0;
			while(next!=0){
				DataChange[next]=1;
				memset(ChangeData[next],0,BLOCK_SIZE);
				next1=next;
				next=NextBlock[next];
				NextBlock[next1]=0;

			}
			fcb[FileDescriptor[fildes]].length=length;
			return 0;

		}
		else{//所截大小 大于文件大小
			if(length-fcb[FileDescriptor[fildes]].length<=BLOCK_SIZE-fcb[FileDescriptor[fildes]].length%BLOCK_SIZE){
				fcb[FileDescriptor[fildes]].length=length;
				return 0;
			}
			int left=length-(BLOCK_SIZE-fcb[FileDescriptor[fildes]].length%BLOCK_SIZE);
			int n=DATA_BEGIN;
			while(left>0){
				for(;n<DISK_BLOCKS;n++){
					if(!DataFree){
						DataChange[n]=1;
						memset(ChangeData[n],0,BLOCK_SIZE);
						left-=BLOCK_SIZE;
					}
				}
				if(n==DISK_BLOCKS){
					fcb[FileDescriptor[fildes]].length=length-left;
					return 0;
				}
			}
			return 0;
		}
	};
private:
	Disk DISK;
	char buffer[BLOCK_SIZE];//缓冲区
	char IntAndString[sizeof(int)];//用于int和string的转换
	char *DiskName;

	int NextBlock[DISK_BLOCKS];//保存块指向的下一个块
	bool DataFree[DISK_BLOCKS];//数据块是否使用
	bool FileExist[MAX_FILE];//是否存在文件
	int FCBBegin,DataBegin;//FCB开始位置，数据块开始位置
	int FileDescriptor[MAX_FILE_DSECRIPTOR+1];//文件描述符，[0]存储此时打开的文件数目

	FCB fcb[MAX_FILE];//存储FCB
	bool DataChange[DISK_BLOCKS];//记录数据块是否被更改
	char ChangeData[DISK_BLOCKS][BLOCK_SIZE];//记录数据块被更改的内容
};