"use client";

import { useQuery } from "@tanstack/react-query";
import axios from "axios";
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

export default function Comments({ postId }: { postId: string }) {
  const getComments = () => axios.get("http://localhost:4000/comments");
  const { isLoading, data, error } = useQuery({
    queryKey: ["comments"],
    queryFn: getComments,
  });

  const selectedComments = data?.data
    ?.filter((comment: Comment) => comment.post === parseInt(postId))
    .sort((a: Comment, b: Comment) => b.id - a.id);

  if (error) error;

  if (isLoading) return <p>Loading...</p>;

  return (
    <div className='my-10 flex flex-col gap-4 shadow-sm'>
      {selectedComments?.map((comment: Comment) => (
        <SingleComment
          author={comment.author}
          contents={comment.contents}
          createdAt={comment.createdAt}
          id={comment.id}
          key={comment.id}
          postId={postId}
        />
      ))}
    </div>
  );
}
