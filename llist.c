#include <stdlib.h>
#include <string.h>

#include "llist.h"

//initialize a linked list
llist_t * ll_init(){
  //TODO
  llist_t *ll = calloc(1,sizeof(llist_t));
  ll->size = 0;
  ll->head = NULL;
  return ll; //clearly it shouldn't return null when you finish it
}

//delete a linked list
void ll_delete(llist_t * ll){
  //TODO
  if(ll->head == NULL)
  {
    free(ll->head);
  }

  ll_node_t * ptr;
  ll_node_t * next;
  ptr = ll->head;
  while(ptr != NULL)
  {
    next = ptr;
    ptr = ptr->next;
    free(next->val);
    free(next);
  }
  free(ll);
}


//push an item onto the front of the list
void ll_push(llist_t * ll, char * s){
  //TODO
  if(s == NULL)
  {
    return;
  }
  ll_node_t *n_Node;
  n_Node = (ll_node_t* )malloc(sizeof(ll_node_t));
  char * space = malloc(strlen(s) + 1);
  strcpy(space, s);

  if(ll->head == NULL)
  {
    n_Node->val = space;
    n_Node->next = NULL;
    ll->head = n_Node;

  }
  else
  {
    n_Node->next = ll->head;
    n_Node->val = space;
    ll->head = n_Node; 
  }
  ll->size = ll->size+1;
}




