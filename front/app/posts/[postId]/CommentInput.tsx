"use client";

import { useMutation, useQueryClient } from "@tanstack/react-query";
import axios from "axios";
import { useState } from "react";

export default function CommentInput({ postId }: { postId: string }) {
  const queryClient = useQueryClient();
  const [commentInput, setCommentInput] = useState<string>("");
  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setCommentInput(event.target.value);
  };

  const { mutate: mutateAddComment } = useMutation({
    mutationFn: async (comment: any) => {
      await axios.post("http://localhost:4000/comments", comment);
    },
    onSettled: () => queryClient.invalidateQueries(["comments"]),
  });

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const commentingData = {
      // 로그인된 user의 이름이 들어갈 예정
      author: "정우",
      contents: commentInput,
      post: parseInt(postId),
      // db에서 자동생성될 예정
      createdAt: new Date(),
      updatedAt: new Date(),
    };
    mutateAddComment(commentingData);
  };

  return (
    <div className='mt-5'>
      <div className='h-[1px] bg-primary' />
      <h2 className='p-2 text-lg font-bold text-primary'>댓글 n개</h2>
      <form method='POST' className='relative' onSubmit={handleSubmit}>
        <textarea
          name='comment'
          id='comment'
          rows={3}
          className='h-[100px] w-full resize-none rounded-md bg-primary/5 p-3'
          placeholder='댓글을 남겨주세요'
          value={commentInput}
          onChange={handleChange}
        ></textarea>
        <p
          className={`absolute bottom-4 left-4 z-20 font-bold  ${
            commentInput.length > 100 ? "text-red-500" : "text-gray-500"
          }`}
        >
          {commentInput.length}/100
        </p>
        <button className='btn-primary btn-sm btn absolute bottom-5 right-4'>
          Send
        </button>
      </form>
    </div>
  );
}
