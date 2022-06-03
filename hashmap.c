#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#include <math.h>
#include <string.h>

#include "llist.h"
#include "hashmap.h"

//local function for hashing an input value (do not edit)
unsigned int _hash(hashmap_t * hm, char * val){
  //based on Java's String.hashcode()
  double k = 0;
  double len = (double) strlen(val);
  int i=1;
  for(char * c = val; *c ; c++,i++){
    k += (*c) * pow(31,len-i);
  }

  return (unsigned int) (((unsigned long) k) % hm->num_buckets);
}


//local function: resize the hashmap by doubling the number of buckets and rehashing
void _resize(hashmap_t * hm){
  //TODO: resize the hashmap if the load gets too high
  int size = hm->num_buckets*2;
  hashmap_t *nHm = calloc(sizeof(hashmap_t), 1);
  llist_t **nBuckets = calloc(size, sizeof(*nBuckets));
  for(int i =0; i< size; i++)
  {
    nBuckets[i] = ll_init();
  }
  nHm->size = 0;
  nHm->num_buckets = size;
  nHm->buckets = nBuckets;

  ll_node_t *ptr;
  for(int i =0; i< hm->num_buckets; i++)
  {
    for(ptr = hm->buckets[i]->head; ptr != NULL; ptr=ptr->next)
    {
      hm_add(nHm, ptr->val);
    }
    ll_delete(hm->buckets[i]);
  }
  free(hm->buckets);
  hm->buckets = nHm->buckets;
  hm->num_buckets = nHm->num_buckets;
  hm->size = nHm->size;
  //hm_delete(nHm);
  //free(hm->buckets);
  free(nHm);
}


//initliaze a hashmap with initial number of buckets
hashmap_t * hm_init(){

  //create the hashmap
  hashmap_t * hm = calloc(sizeof(hashmap_t),1);
llist_t ** nBuckets = calloc(HM_INIT_NUM_BUCKETS, sizeof(llist_t*));
hm->buckets = nBuckets;
for(int i=0; i< HM_INIT_NUM_BUCKETS; i++)
{
  nBuckets[i] = ll_init();
}
hm->num_buckets= HM_INIT_NUM_BUCKETS;
hm->size = 0;


  
  
  return hm;
}


//delete/deallocate the hashmap
void hm_delete(hashmap_t * hm){

  //TODO: properly deallocate
for(int i=0; i<hm->num_buckets;i++)
{
  ll_delete(hm->buckets[i]);
}

  //delete the hashmap
  hm->size = 0;
  hm-> num_buckets = 0;
  free(hm->buckets);
  free(hm);
}

//add a string value to the hashmap
void hm_add(hashmap_t * hm, char * val){
  // You get this function for free :)
  
  //check the load on the hashtable, if > max load, resize!
  if(((hm->size+1)/(double) hm->num_buckets) > HM_MAX_LOAD){
    _resize(hm); 
  }

  unsigned int h = _hash(hm,val); //get hash
  ll_push(hm->buckets[h],val); //note that list does string dup
  hm->size++; //increase size


}

//see if a string value is in the hashmap
bool hm_check(hashmap_t * hm, char * val){

  //TODO check if a value is in the hashmap
  unsigned int h = _hash(hm,val);
  ll_node_t *ptr;
  ptr = hm->buckets[h]->head;
  while(ptr !=NULL)
  {
    if(strcmp(ptr->val,val)== 0)
    {
      return true;
    }
    ptr = ptr->next;
  }

  return false; //otherwise false
}


