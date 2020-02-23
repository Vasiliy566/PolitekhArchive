//
// Created by Vasiliy Isaev on 2019-12-24.
//

#ifndef UNTITLED3_MYLIST_H
#define UNTITLED3_MYLIST_H
#include<cassert>

template <class T>
class myList {



public:
    struct Node {
        T     value;
        Node *next;
        Node(const T & val, Node * nex) : value(val), next(nex) {}
    };
    Node *  mHead;
    int    mCount;

    class Iterator {
    public:
        Iterator(Node* curr) : mCurr(curr) {}
        T & operator*() {
            assert(mCurr != NULL);
            return mCurr->value;
        }
        const T & operator*() const {
            assert(mCurr != NULL);
            return mCurr->value;
        }
        void operator++() const {
            assert(mCurr != NULL);
            mCurr = mCurr->next;
        }
        bool operator==(const Iterator & other) const {
            return (mCurr == other.mCurr);
        }
        bool operator!=(const Iterator & other) const {
            return (mCurr != other.mCurr);
        }
    private:
        mutable Node * mCurr;
    };

    typedef Iterator iterator;
    typedef const Iterator const_iterator;
    typedef T& reference;
    typedef const T& const_reference;
    typedef T* pointer;
    typedef const T* const_pointer;

    const_reference front() const {
        assert(mHead != NULL);
        return mHead->value;
    }
    reference front() {
        assert(mHead != NULL);
        return mHead->value;
    }

    iterator begin() {
        return Iterator(mHead);
    }
    iterator end() {
        return Iterator(NULL);
    }
    const_iterator begin() const {
        return Iterator(mHead);
    }
    const_iterator end() const  {
        return Iterator(NULL);
    }
    void remove(const iterator & o) {
        for (Node * n = mHead, *p = 0; n; p=n, n=n->next) {
            if (n->value == *o) {
                (p ? p->next : mHead) = n->next;
                --mCount;
                delete n;
                return;
            }
        }
    }

    myList() : mHead(0), mCount(0) {}
    ~myList() { destroy(mHead); }

    void destroy(Node* r) {
        Node * n = r;
        while (n) {
            r = n;
            n = n->next;
            delete r;
        }
    }
    void insert(const T & value) {
        mHead = new Node(value, mHead);
        ++mCount;
    }


    void remove(const T & value) {
        for (Node * n = mHead, *p = 0; n; p=n, n=n->next) {
            if (n->value == value) {
                (p ? p->next : mHead) = n->next;
                --mCount;
                delete n;
                return;
            }
        }
    }
    bool contains(const T & value) const {
        for (const iterator & b = begin(), & e = end(); b != e; ++b) {
            if (*b == value) return true;
        }
        return false;
    }
    bool empty() const {
        return (mHead == 0);
    }
    int size() const {
        return mCount;
    }
};
#endif //UNTITLED3_MYLIST_H
