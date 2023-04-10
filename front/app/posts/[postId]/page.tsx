"use client";

import Container from "@/app/components/Container";
import MainImage from "@/app/components/MainImage";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import axios, { AxiosError } from "axios";
import Link from "next/link";
import { usePathname, useRouter } from "next/navigation";
import toast from "react-hot-toast";
import CommentInput from "./CommentInput";
import Comments from "./Comments";

export default function Page() {
  const router = useRouter();
  const client = useQueryClient();
  let postToastId: string;

  const pathName = usePathname();
  const postId = pathName.split("/")[2];

  const getPost = () => axios.get(`http://localhost:4000/posts/${postId}`);
  const { isLoading, data, error } = useQuery({
    queryKey: [`posts/${postId}`, "posts"],
    queryFn: getPost,
  });
  const post = data?.data;

  const { mutate: mutateDeletePost } = useMutation({
    mutationFn: async () =>
      await axios.delete(`http://localhost:4000/posts/${postId}`),
    onSuccess: () => {
      toast.success("게시물을 삭제하였습니다.", { id: postToastId });
    },
    onError: (error) => {
      if (error instanceof AxiosError) {
        toast.error("게시물 삭제 중 에러 발생", { id: postToastId });
      }
    },
    onSettled: () => {
      client.invalidateQueries(["posts", `posts/${postId}`]);
      router.replace("/posts");
      router.refresh();
    },
  });
  const handleDelete = () => {
    if (confirm("게시물을 삭제하시겠습니까?")) {
      mutateDeletePost();
    }
    return;
  };

  if (error) error;

  if (isLoading) return null;

  return (
    <>
      <MainImage
        src={post?.imgUrl}
        title={post?.title}
        createdAt={post?.createdAt}
        author={post?.author}
      />
      <Container>
        <p
          dangerouslySetInnerHTML={{ __html: post?.contents }}
          className='prose max-w-none prose-p:m-0'
        />
        <div className='mt-4 flex justify-end gap-2'>
          <Link
            href={`/posts/${postId}/edit`}
            className='btn-outline btn-primary btn-xs btn text-sm font-thin'
          >
            수정
          </Link>
          <button
            className='btn-outline btn-error btn-xs btn text-sm font-thin text-white'
            onClick={handleDelete}
          >
            삭제
          </button>
        </div>
        <CommentInput postId={postId} />
        <Comments postId={postId} />
      </Container>
    </>
  );
}
