"use client";

import { useState, useEffect } from "react";
import SingleComment from "./SingleComment";

export const fetchCache = "force-no-store";

type Comment = {
  id: number;
  author: string;
  contents: string;
  post: number;
  createdAt: string;
  updatedAt: string;
};

export default function Comments({ postId }: { postId: number }) {
  const [comments, setComments] = useState<Comment[]>();
  const getComments = () => {
    fetch("http://localhost:4000/comments")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setComments(data);
      });
  };
  useEffect(() => {
    getComments();
  }, []);

  const selectedComments = comments
    ?.filter((comment: Comment) => comment.post == postId)
    .sort((a: Comment, b: Comment) => b.id - a.id);

  return (
    <div className='my-10 flex flex-col gap-4 shadow-sm'>
      {selectedComments?.map((comment: Comment) => (
        <SingleComment
          author={comment.author}
          contents={comment.contents}
          createdAt={comment.createdAt}
          id={comment.id}
          key={comment.id}
        />
      ))}
    </div>
  );
}
